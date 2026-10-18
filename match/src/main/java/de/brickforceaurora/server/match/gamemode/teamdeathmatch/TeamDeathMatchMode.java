package de.brickforceaurora.server.match.gamemode.teamdeathmatch;

import de.brickforceaurora.server.match.gamemode.GameData;
import de.brickforceaurora.server.match.gamemode.TeamGameMode;
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
        context.manager().broadcast(new ClientboundCreateRoomPacket().roomInfo(room));
        context.manager().broadcast(new ClientboundAddRoomPacket().roomInfo(room));
    }

    @Override
    public void handleRoomJoin(NetManager<?> netManager, Room room, BFClient client) {
        //TODO: Hardcoded info needs to change
        TDMData gameData = (TDMData) room.gameData();
        client.send(new ClientboundRoomConfigPacket().mapId(gameData.mapId()).mapAlias(gameData.mapAlias()).
                weaponOption(gameData.weaponOption()).timeLimit(gameData.timeLimit()).killCount(0).canJoinMidGame(gameData.canJoinMidGame()).
                autoBalance(gameData.autoBalance()).allowBuildGun(false).password("").commented(0).roomType(roomType()).drop(gameData.drop()).wanted(gameData.wanted()));

        boolean[] slotLocks = room.getSlotLocksByMaxPlayers();
        for (int i = 0; i < slotLocks.length; i++) {
            client.send(
                    new ClientboundSlotLockPacket()
                            .index((byte) i)
                            .slotLocked(slotLocks[i])
            );
        }
        client.send(new ClientboundRendezvousInfoPacket().ip(client.ip()).port(client.port()));
        client.send(new ClientboundMasterPacket().ownerClientId(client.id()));
        //TODO: Hardcoded info needs to change
        room.broadcast(new ClientboundEnterPacket().id(client.id()).nickname(client.name()).localIp(client.ip())
                .localPort(client.port()).remoteIp(client.ip()).remotePort(client.port())
                .equipment(List.of("wau", "wax", "wba", "wap", "aac", "aad", "s07")).status(1).xp(9400000).clanId(0).clanName("Clan")
                .clanMark(0).rank(65).playerFlag(1).weaponChanges(List.of()).dropItems(List.of()));
        ClientboundSlotInfoPacket slot = new ClientboundSlotInfoPacket().id(client.id()).slot((byte)0).status(ClientStatus.WAITING.id()).kill(0)
                .kill(0).death(0).assist(0).score(0).mission(0);
        room.broadcast(slot);
        client.send(slot);
    }

    @Override
    public void handleRoomUpdate(Room room, NetContext<ServerboundRoomConfigPacket> context) {

        //update current room with the provided data
        //if bungee cache map
        //if bnd unpack timer and set times and repeat values, cache map as well, so init specifics
        context.client().send(new ClientboundRoomConfigPacket());
        context.client().send(new ClientboundUpdateRoomPacket().roomInfo(room));
    }

    @Override
    public void handleTeamChange(Room room, NetContext<ServerboundTeamChangePacket> context) {

    }

}
