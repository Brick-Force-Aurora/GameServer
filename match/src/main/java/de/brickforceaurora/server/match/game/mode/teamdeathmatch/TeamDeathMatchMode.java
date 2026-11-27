package de.brickforceaurora.server.match.game.mode.teamdeathmatch;

import java.util.ArrayList;
import java.util.List;

import de.brickforceaurora.server.ServerApp;
import de.brickforceaurora.server.database.DatabaseException;
import de.brickforceaurora.server.database.UserRepository;
import de.brickforceaurora.server.database.model.UserProfile;
import de.brickforceaurora.server.database.model.UserStats;
import de.brickforceaurora.server.match.MatchServerApp;
import de.brickforceaurora.server.match.game.GameData;
import de.brickforceaurora.server.match.game.PlayerStats;
import de.brickforceaurora.server.match.game.TeamGameData;
import de.brickforceaurora.server.match.game.TeamGameMode;
import de.brickforceaurora.server.match.item.Inventories;
import de.brickforceaurora.server.match.listener.ClientListener;
import de.brickforceaurora.server.match.room.PeerEndpoints;
import de.brickforceaurora.server.match.room.Room;
import de.brickforceaurora.server.match.room.RoomManager;
import de.brickforceaurora.server.net.tcp.BFClient;
import de.brickforceaurora.server.net.tcp.NetContext;
import de.brickforceaurora.server.net.tcp.NetManager;
import de.brickforceaurora.server.net.tcp.protocol.clientbound.original.ClientboundAddRoomPacket;
import de.brickforceaurora.server.net.tcp.protocol.clientbound.original.ClientboundCreateRoomPacket;
import de.brickforceaurora.server.net.tcp.protocol.clientbound.original.ClientboundEmptyCannonPacket;
import de.brickforceaurora.server.net.tcp.protocol.clientbound.original.ClientboundEmptyTrainPacket;
import de.brickforceaurora.server.net.tcp.protocol.clientbound.original.ClientboundEnterPacket;
import de.brickforceaurora.server.net.tcp.protocol.clientbound.original.ClientboundJoinPacket;
import de.brickforceaurora.server.net.tcp.protocol.clientbound.original.ClientboundMasterPacket;
import de.brickforceaurora.server.net.tcp.protocol.clientbound.original.ClientboundRendezvousInfoPacket;
import de.brickforceaurora.server.net.tcp.protocol.clientbound.original.ClientboundRoomConfigPacket;
import de.brickforceaurora.server.net.tcp.protocol.clientbound.original.ClientboundSlotInfoPacket;
import de.brickforceaurora.server.net.tcp.protocol.clientbound.original.ClientboundSlotLockPacket;
import de.brickforceaurora.server.net.tcp.protocol.clientbound.original.ClientboundTeamChangeFailPacket;
import de.brickforceaurora.server.net.tcp.protocol.clientbound.original.ClientboundTeamChangePacket;
import de.brickforceaurora.server.net.tcp.protocol.clientbound.original.ClientboundTeamMatchEndPacket;
import de.brickforceaurora.server.net.tcp.protocol.clientbound.original.ClientboundUpdateRoomPacket;
import de.brickforceaurora.server.net.tcp.protocol.data.ClientStatus;
import de.brickforceaurora.server.net.tcp.protocol.data.ResultUnit;
import de.brickforceaurora.server.net.tcp.protocol.data.RoomStatus;
import de.brickforceaurora.server.net.tcp.protocol.data.RoomType;
import de.brickforceaurora.server.net.tcp.protocol.serverbound.original.ServerboundCreateRoomPacket;
import de.brickforceaurora.server.net.tcp.protocol.serverbound.original.ServerboundRoomConfigPacket;
import de.brickforceaurora.server.net.tcp.protocol.serverbound.original.ServerboundTeamChangePacket;
import me.lauriichan.snowframe.SnowFrame;
import me.lauriichan.snowframe.extension.Extension;

@Extension
public class TeamDeathMatchMode extends TeamGameMode<TDMData> {

    private final RoomManager roomManager;
    private final MatchServerApp app;

    public TeamDeathMatchMode(final SnowFrame<MatchServerApp> snowFrame, final RoomManager roomManager) {
        super(snowFrame, TDMData.class, RoomType.TEAM_MATCH);
        this.roomManager = roomManager;
        this.app = snowFrame.app();
    }

    @Override
    public GameData createGameDataFor(final Room room) {
        return new TDMData();
    }

    @Override
    public void handleRoomCreation(final Room room, final NetContext<ServerboundCreateRoomPacket> context) {
        final ServerboundCreateRoomPacket pkt = context.packet();

        //add provided data
        //add client to room
        final TDMData gameData = (TDMData) room.gameData();
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
    public void handleRoomJoin(final NetManager<?> netManager, final Room room, final BFClient client) {
        //TODO: Hardcoded info needs to change
        final TDMData gameData = (TDMData) room.gameData();
        client.send(new ClientboundJoinPacket().roomId(room.id()));
        client.send(configPacket(room, gameData, client));

        final boolean[] slotLocks = room.getSlotLocksByMaxPlayers();
        for (int i = 0; i < slotLocks.length; i++) {
            client.send(new ClientboundSlotLockPacket().index((byte) i).slotLocked(slotLocks[i]));
        }
        final ServerApp<?> serverImpl = netManager.snowFrame().app();
        client.send(new ClientboundRendezvousInfoPacket().ip(serverImpl.host()).port(serverImpl.port()));
        serverImpl.server().rendezvousManager().pushPong(client);
        client.send(new ClientboundMasterPacket().ownerClientId(room.masterId()));

        for (final BFClient member : room.clients().toArray(new BFClient[0])) {
            client.send(enterPacket(member), slotInfoPacket(member));
        }
        room.broadcast(enterPacket(client));
        final ClientboundSlotInfoPacket slot = slotInfoPacket(client);
        room.broadcast(slot);
        client.send(slot);
    }

    private ClientboundEnterPacket enterPacket(final BFClient client) {
        // hide real ip address
        final PeerEndpoints endpoints = PeerEndpoints.HIDDEN;
        final UserProfile profile = client.attr(ClientListener.ATTR_PROFILE, UserProfile.class);
        // TODO Clans, no clan = -1
        return new ClientboundEnterPacket().id(client.id()).nickname(client.name()).localIp(endpoints.localIp())
            .localPort(endpoints.localPort()).remoteIp(endpoints.remoteIp()).remotePort(endpoints.remotePort())
            .equipment(Inventories.equipment(app.userRepository(), client)).status(1).xp(profile == null ? 0 : profile.xp()).clanId(-1).clanName("").clanMark(-1)
            .rank(profile == null ? 0 : profile.rank()).playerFlag(1).weaponChanges(List.of()).dropItems(List.of());
    }

    private ClientboundSlotInfoPacket slotInfoPacket(final BFClient client) {
        final Integer slot = client.attr(Room.ATTR_SLOT, Integer.class);
        final ClientStatus status = client.attr(Room.ATTR_STATUS, ClientStatus.class);
        return new ClientboundSlotInfoPacket().id(client.id()).slot(slot == null ? (byte) 0 : slot.byteValue())
            .status((status == null ? ClientStatus.WAITING : status).id()).kill(0).death(0).assist(0).score(0).mission(0);
    }

    @Override
    public void handleRoomUpdate(final Room room, final NetContext<ServerboundRoomConfigPacket> context) {
        final ServerboundRoomConfigPacket packet = context.packet();
        if (context.client().id() != room.masterId() || room.status() != RoomStatus.WAITING
            || !(room.gameData() instanceof final TDMData data)) {
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
        final String password = packet.pswd();
        room.password(password == null || password.isEmpty() ? null : password);
        for (final BFClient member : room.clients().toArray(new BFClient[0])) {
            member.send(configPacket(room, data, member));
        }
        context.manager().broadcast(new ClientboundUpdateRoomPacket().roomInfo(room));
    }

    private ClientboundRoomConfigPacket configPacket(final Room room, final TDMData data, final BFClient receiver) {
        //TODO: Hardcoded info needs to change
        // Only the master gets the password, for the room config dialog
        final String password = receiver.id() == room.masterId() && room.password() != null ? room.password() : "";
        return new ClientboundRoomConfigPacket().mapId(data.mapId()).mapAlias(data.mapAlias()).weaponOption(data.weaponOption())
            .timeLimit(data.timeLimit()).killCount(data.goal()).canJoinMidGame(data.canJoinMidGame()).autoBalance(data.autoBalance())
            .allowBuildGun(false).password(password).commented(0).roomType(roomType()).drop(data.drop()).wanted(data.wanted());
    }

    //TODO: sollte zu allen TEAM Modes
    @Override
    public void handleTeamChange(final Room room, final NetContext<ServerboundTeamChangePacket> context) {
        final BFClient client = context.client();
        final ServerboundTeamChangePacket packet = context.packet();
        final Integer current = client.attr(Room.ATTR_SLOT, Integer.class);
        final ClientStatus status = client.attr(Room.ATTR_STATUS, ClientStatus.class);
        // The client waits for an answer, so there always has to be one
        if (status != ClientStatus.WAITING) {
            client.send(new ClientboundTeamChangeFailPacket().reason(0));
            return;
        }
        final int target = packet.clickSlot() ? packet.slotNum()
            : TeamGameData.isRed(current) ? room.freeSlot(TeamGameData.FIRST_BLUE_SLOT, Integer.MAX_VALUE)
                : room.freeSlot(0, TeamGameData.FIRST_BLUE_SLOT);
        if (target == current || !room.moveToSlot(client, target)) {
            client.send(new ClientboundTeamChangeFailPacket().reason(0));
            return;
        }
        room.broadcast(new ClientboundTeamChangePacket().clientId(client.id()).previousSlot(current).slot(target));
    }

    @Override
    public void handleRoomLeave(final Room room, final BFClient client) {
        if (!(room.gameData() instanceof final TDMData data)) {
            return;
        }
        // The cannons and trains of the player are free again
        for (final int cannon : data.releaseCannons(client.id())) {
            room.broadcast(new ClientboundEmptyCannonPacket().cannon(cannon));
        }
        for (final int train : data.releaseTrains(client.id())) {
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

    @Override
    public void handleMasterChange(final Room room) {
        if (!(room.gameData() instanceof final TDMData data)) {
            return;
        }
        // Only the master gets the password, so the config has to be sent again
        for (final BFClient member : room.clients().toArray(new BFClient[0])) {
            member.send(configPacket(room, data, member));
        }
        if (room.status() == RoomStatus.PENDING) {
            room.status(RoomStatus.PLAYING);
            roomManager.updateRoom(room);
        }
    }

    public void resultsConfirmed(final Room room) {
        room.status(RoomStatus.WAITING);
        roomManager.updateRoom(room);
    }

    public void endMatch(final NetManager<?> manager, final Room room, final TDMData data) {
        if (!data.finishMatch()) {
            return;
        }
        final int redScore = data.redScore();
        final int blueScore = data.blueScore();
        final byte endCode = (byte) Integer.compare(redScore, blueScore);

        final UserRepository users = app.userRepository();
        final List<ResultUnit> results = new ArrayList<>();
        final List<Integer> present = new ArrayList<>();
        int redKill = 0, redDeath = 0, blueKill = 0, blueDeath = 0;
        for (final PlayerStats stats : data.allStats()) {
            final BFClient member = room.clientById(stats.clientId());
            if (member == null) {
                // Left the room during the match
                continue;
            }
            present.add(member.id());
            final int result = resultFor(endCode, stats.red());
            final int xp = TDMScoring.xp(stats, result);
            final int points = TDMScoring.points(stats, result);
            final int prevXp = save(manager, users, member, stats, xp, points);
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
        final ResultUnit[] units = results.toArray(ResultUnit[]::new);
        for (final BFClient member : room.clients().toArray(new BFClient[0])) {
            final PlayerStats stats = data.stats(member.id());
            final Integer slot = member.attr(Room.ATTR_SLOT, Integer.class);
            final boolean red = stats != null ? stats.red() : slot == null || TeamGameData.isRed(slot);
            member.send(new ClientboundTeamMatchEndPacket().endCode((byte) resultFor(endCode, red)).redKill(redKill).redDeath(redDeath)
                .blueKill(blueKill).blueDeath(blueDeath).results(units));
        }
        manager.broadcast(new ClientboundUpdateRoomPacket().roomInfo(room));
        manager.logger().info("Room {0} ({1}) ended: red {2}, blue {3}", room.id(), room.title(), redScore, blueScore);
    }

    private static int resultFor(final int endCode, final boolean red) {
        return endCode == 0 ? 0 : endCode > 0 == red ? 1 : -1;
    }

    private int save(final NetManager<?> manager, final UserRepository users, final BFClient client, final PlayerStats stats, final int xp,
        final int points) {
        final Integer accountId = client.attr(ClientListener.ATTR_ACCOUNT_ID, Integer.class);
        final UserProfile profile = client.attr(ClientListener.ATTR_PROFILE, UserProfile.class);
        if (accountId == null || profile == null) {
            return 0;
        }
        try {
            final UserProfile updated = new UserProfile(profile.accountId(), profile.nickname(), profile.xp() + xp, profile.rank(),
                profile.tutorials(), profile.countryFilter(), profile.tosAccepted(), profile.extraSlots(), profile.firstLoginFp(),
                profile.point() + points, profile.brick(), profile.cash(), profile.freeCoin(), profile.starDust());
            users.saveProfile(updated);
            client.attrSet(ClientListener.ATTR_PROFILE, updated);
            users.loadStats(accountId)
                .ifPresent(total -> users.saveStats(new UserStats(total.accountId(), total.kill() + stats.kill(),
                    total.death() + stats.death(), total.assist() + stats.assist(), total.mission(), total.score() + stats.score(),
                    total.heavy(), total.assault(), total.sniper(), total.subMachine(), total.handGun(), total.melee(), total.special())));
            return profile.xp();
        } catch (final DatabaseException exception) {
            manager.logger().error("Couldn't save the result of {0}", exception, client);
            return 0;
        }
    }

}
