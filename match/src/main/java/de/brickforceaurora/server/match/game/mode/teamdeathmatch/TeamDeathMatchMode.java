package de.brickforceaurora.server.match.game.mode.teamdeathmatch;

import de.brickforceaurora.server.IBrickForceServer;
import de.brickforceaurora.server.database.DatabaseException;
import de.brickforceaurora.server.database.UserRepository;
import de.brickforceaurora.server.database.model.UserProfile;
import de.brickforceaurora.server.database.model.UserStats;
import de.brickforceaurora.server.match.listener.ClientListener_;
import de.brickforceaurora.server.match.item.Inventories;
import de.brickforceaurora.server.match.MatchServerApp;
import de.brickforceaurora.server.match.game.GameData;
import de.brickforceaurora.server.match.game.PlayerStats;
import de.brickforceaurora.server.match.game.TeamGameData;
import de.brickforceaurora.server.match.game.TeamGameMode;
import de.brickforceaurora.server.net.protocol.data.ResultUnit;
import de.brickforceaurora.server.net.protocol.data.RoomStatus;
import de.brickforceaurora.server.match.room.PeerEndpoints;
import de.brickforceaurora.server.match.room.Room;
import de.brickforceaurora.server.net.BFClient;
import de.brickforceaurora.server.net.NetContext;
import de.brickforceaurora.server.net.NetManager;
import de.brickforceaurora.server.net.protocol.clientbound.original.*;
import de.brickforceaurora.server.net.protocol.data.ClientStatus;
import de.brickforceaurora.server.net.protocol.data.RoomType;
import de.brickforceaurora.server.net.protocol.serverbound.original.*;
import me.lauriichan.snowframe.SnowFrame;
import me.lauriichan.snowframe.extension.Extension;

import java.util.ArrayList;
import java.util.List;

@Extension
public class TeamDeathMatchMode extends TeamGameMode<TDMData> {

    public TeamDeathMatchMode(final SnowFrame<?> snowFrame) {
        super(snowFrame, TDMData.class, RoomType.TEAM_MATCH);
    }

    @Override
    public GameData createGameDataFor(Room room) {
        return new TDMData();
    }

    @Override
    public void handleRoomCreation(Room room, NetContext<ServerboundCreateRoomPacket> context) {
        ServerboundCreateRoomPacket pkt = context.packet();

        //add provided data
        //add client to room
        TDMData gameData = (TDMData) room.gameData();
        gameData.goal(pkt.parameters()[0]);
        gameData.timeLimit(pkt.parameters()[1]);
        gameData.weaponOption(pkt.parameters()[2]);
        gameData.mapId(pkt.parameters()[3]);
        gameData.canJoinMidGame(pkt.parameters()[4] >= 1);
        gameData.autoBalance(pkt.parameters()[5] >= 1);
        gameData.wanted(pkt.parameters()[6] >= 1);
        gameData.drop(pkt.parameters()[7] >= 1);
        gameData.mapAlias(pkt.alias());
        context.client().send(new ClientboundCreateRoomPacket().roomInfo(room));
        context.manager().broadcast(new ClientboundAddRoomPacket().roomInfo(room));
    }

    @Override
    public void handleRoomJoin(NetManager<?> netManager, Room room, BFClient client) {
        //TODO: Hardcoded info needs to change
        TDMData gameData = (TDMData) room.gameData();
        client.send(new ClientboundJoinPacket().roomId(room.id()));
        client.send(configPacket(room, gameData, client));

        boolean[] slotLocks = room.getSlotLocksByMaxPlayers();
        for (int i = 0; i < slotLocks.length; i++) {
            client.send(new ClientboundSlotLockPacket().index((byte) i).slotLocked(slotLocks[i]));
        }
        IBrickForceServer serverImpl = netManager.snowFrame().app();
        client.send(new ClientboundRendezvousInfoPacket().ip(serverImpl.host()).port(serverImpl.port()));
        serverImpl.server().rendezvousManager().pushPong(client);
        client.send(new ClientboundMasterPacket().ownerClientId(room.masterId()));

        for (BFClient member : room.clients().toArray(new BFClient[0])) {
            client.send(enterPacket(member), slotInfoPacket(member));
        }
        room.broadcast(enterPacket(client));
        ClientboundSlotInfoPacket slot = slotInfoPacket(client);
        room.broadcast(slot);
        client.send(slot);
    }

    private static ClientboundEnterPacket enterPacket(BFClient client) {
        // hide real ip address
        PeerEndpoints endpoints = PeerEndpoints.HIDDEN;
        //TODO: Hardcoded info needs to change
        return new ClientboundEnterPacket().id(client.id()).nickname(client.name()).localIp(endpoints.localIp())
            .localPort(endpoints.localPort()).remoteIp(endpoints.remoteIp()).remotePort(endpoints.remotePort())
            .equipment(Inventories.equipment(client)).status(1).xp(9400000).clanId(0).clanName("Clan").clanMark(0).rank(65).playerFlag(1)
            .weaponChanges(List.of()).dropItems(List.of());
    }

    private static ClientboundSlotInfoPacket slotInfoPacket(BFClient client) {
        Integer slot = client.attr(Room.ATTR_SLOT, Integer.class);
        ClientStatus status = client.attr(Room.ATTR_STATUS, ClientStatus.class);
        return new ClientboundSlotInfoPacket().id(client.id()).slot(slot == null ? (byte) 0 : slot.byteValue())
            .status((status == null ? ClientStatus.WAITING : status).id()).kill(0).death(0).assist(0).score(0).mission(0);
    }

    @Override
    public void handleRoomUpdate(Room room, NetContext<ServerboundRoomConfigPacket> context) {
        ServerboundRoomConfigPacket packet = context.packet();
        if (context.client().id() != room.masterId() || room.status() != RoomStatus.WAITING || !(room.gameData() instanceof TDMData data)) {
            return;
        }
        data.goal(packet.killCount());
        data.timeLimit(packet.timeLimit());
        data.weaponOption(packet.weaponOption());
        data.mapId(packet.nWhere());
        data.canJoinMidGame(packet.breakInto() >= 1);
        data.autoBalance(packet.teamBalance() >= 1);
        data.wanted(packet.useBuildGun() >= 1);
        data.drop(packet.itemPickup() >= 1);
        data.mapAlias(packet.whereAlias());
        String password = packet.pswd();
        room.password(password == null || password.isEmpty() ? null : password);
        for (BFClient member : room.clients().toArray(new BFClient[0])) {
            member.send(configPacket(room, data, member));
        }
        context.manager().broadcast(new ClientboundUpdateRoomPacket().roomInfo(room));
    }

    private ClientboundRoomConfigPacket configPacket(Room room, TDMData data, BFClient receiver) {
        //TODO: Hardcoded info needs to change
        // Only the master gets the password, for the room config dialog
        String password = receiver.id() == room.masterId() && room.password() != null ? room.password() : "";
        return new ClientboundRoomConfigPacket().mapId(data.mapId()).mapAlias(data.mapAlias()).weaponOption(data.weaponOption())
            .timeLimit(data.timeLimit()).killCount(data.goal()).canJoinMidGame(data.canJoinMidGame()).autoBalance(data.autoBalance())
            .allowBuildGun(false).password(password).commented(0).roomType(roomType()).drop(data.drop()).wanted(data.wanted());
    }

    @Override
    public void handleTeamChange(Room room, NetContext<ServerboundTeamChangePacket> context) {
        BFClient client = context.client();
        ServerboundTeamChangePacket packet = context.packet();
        Integer current = client.attr(Room.ATTR_SLOT, Integer.class);
        ClientStatus status = client.attr(Room.ATTR_STATUS, ClientStatus.class);
        // The client waits for an answer, so there always has to be one
        if (current == null || room.status() != RoomStatus.WAITING || (client.id() != room.masterId() && status == ClientStatus.READY)) {
            client.send(new ClientboundTeamChangeFailPacket().reason(0));
            return;
        }
        int target = packet.clickSlot() ? packet.slotNum()
            : TeamGameData.isRed(current) ? room.freeSlot(TeamGameData.FIRST_BLUE_SLOT, Integer.MAX_VALUE)
                : room.freeSlot(0, TeamGameData.FIRST_BLUE_SLOT);
        if (target == current || !room.moveToSlot(client, target)) {
            client.send(new ClientboundTeamChangeFailPacket().reason(0));
            return;
        }
        room.broadcast(new ClientboundTeamChangePacket().clientId(client.id()).previousSlot(current).slot(target));
    }

    @Override
    public void handleRoomLeave(Room room, BFClient client) {
        if (!(room.gameData() instanceof TDMData data)) {
            return;
        }
        // The cannons and trains of the player are free again
        for (int cannon : data.releaseCannons(client.id())) {
            room.broadcast(new ClientboundEmptyCannonPacket().cannon(cannon));
        }
        for (int train : data.releaseTrains(client.id())) {
            room.broadcast(new ClientboundEmptyTrainPacket().train(train));
        }
        if (room.status() != RoomStatus.MATCH_END) {
            return;
        }
        // The player can't confirm the result screen anymore
        if (data.resultDone(client.id())) {
            resultsConfirmed(room);
        }
    }

    public void resultsConfirmed(Room room) {
        room.status(RoomStatus.WAITING);
        MatchServerApp.app().roomManager().updateRoom(room);
    }

    public void endMatch(NetManager<?> manager, Room room, TDMData data) {
        if (!data.finishMatch()) {
            return;
        }
        int redScore = data.redScore();
        int blueScore = data.blueScore();
        byte endCode = (byte) Integer.compare(redScore, blueScore);

        UserRepository users = new UserRepository(MatchServerApp.database());
        List<ResultUnit> results = new ArrayList<>();
        List<Integer> present = new ArrayList<>();
        int redKill = 0, redDeath = 0, blueKill = 0, blueDeath = 0;
        for (PlayerStats stats : data.allStats()) {
            BFClient member = room.clientById(stats.clientId());
            if (member == null) {
                // Left the room during the match
                continue;
            }
            present.add(member.id());
            int result = endCode == 0 ? 0 : (endCode > 0) == stats.red() ? 1 : -1;
            int xp = TdmScoring.xp(stats, result);
            int points = TdmScoring.points(stats, result);
            int prevXp = save(manager, users, member, stats, xp, points);
            results.add(new ResultUnit(stats.red(), stats.clientId(), stats.nickname(), stats.kill(), stats.death(), stats.assist(),
                stats.score(), points, xp, 0, prevXp, prevXp + xp, 0L));
            if (stats.red()) {
                redKill += stats.kill();
                redDeath += stats.death();
            } else {
                blueKill += stats.kill();
                blueDeath += stats.death();
            }
        }
        data.pendingResults(present);
        room.status(RoomStatus.MATCH_END);
        room.broadcast(new ClientboundTeamMatchEndPacket().endCode(endCode).redKill(redKill).redDeath(redDeath).blueKill(blueKill)
            .blueDeath(blueDeath).results(results.toArray(ResultUnit[]::new)));
        manager.broadcast(new ClientboundUpdateRoomPacket().roomInfo(room));
        manager.logger().info("Room {0} ({1}) ended: red {2}, blue {3}", room.id(), room.title(), redScore, blueScore);
    }

    private int save(NetManager<?> manager, UserRepository users, BFClient client, PlayerStats stats, int xp, int points) {
        Integer accountId = client.attr(ClientListener_.ATTR_ACCOUNT_ID, Integer.class);
        if (accountId == null) {
            return 0;
        }
        try {
            UserProfile profile = users.loadProfile(accountId).orElse(null);
            if (profile == null) {
                return 0;
            }
            users.saveProfile(new UserProfile(profile.accountId(), profile.nickname(), profile.xp() + xp, profile.rank(),
                profile.tutorials(), profile.countryFilter(), profile.tosAccepted(), profile.extraSlots(), profile.firstLoginFp(),
                profile.point() + points, profile.brick(), profile.cash(), profile.freeCoin(), profile.starDust()));
            users.loadStats(accountId)
                .ifPresent(total -> users.saveStats(new UserStats(total.accountId(), total.kill() + stats.kill(),
                    total.death() + stats.death(), total.assist() + stats.assist(), total.mission(), total.score() + stats.score(),
                    total.heavy(), total.assault(), total.sniper(), total.subMachine(), total.handGun(), total.melee(), total.special())));
            return profile.xp();
        } catch (DatabaseException exception) {
            manager.logger().error("Couldn't save the result of {0}", exception, client);
            return 0;
        }
    }

}
