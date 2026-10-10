package de.brickforceaurora.server.match.gamemode.teamdeathmatch;

import de.brickforceaurora.server.match.gamemode.GameData;
import de.brickforceaurora.server.match.gamemode.TeamGameMode;
import de.brickforceaurora.server.match.room.Room;
import de.brickforceaurora.server.net.NetContext;
import de.brickforceaurora.server.net.protocol.clientbound.original.*;
import de.brickforceaurora.server.net.protocol.data.ClientStatus;
import de.brickforceaurora.server.net.protocol.data.RoomStatus;
import de.brickforceaurora.server.net.protocol.data.RoomType;
import de.brickforceaurora.server.net.protocol.serverbound.original.ServerboundCreateRoomPacket;
import de.brickforceaurora.server.net.protocol.serverbound.original.ServerboundLeavePacket;
import de.brickforceaurora.server.net.protocol.serverbound.original.ServerboundResumeRoomPacket;
import de.brickforceaurora.server.net.protocol.serverbound.original.ServerboundRoomConfigPacket;
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
        gameData.goal = pkt.parameters()[0];
        gameData.timelimit = pkt.parameters()[1];
        gameData.weaponOption = pkt.parameters()[2];
        gameData.mapId = pkt.parameters()[3];
        gameData.canJoinMidGame = pkt.parameters()[4] >= 1;
        gameData.autoBalance = pkt.parameters()[5] >= 1;
        gameData.wanted = pkt.parameters()[6] >= 1;
        gameData.drop = pkt.parameters()[7] >= 1;
        gameData.mapAlias = pkt.alias();
        context.client().send(new ClientboundRendezvousInfoPacket().ip(context.client().ip()).port(context.client().port()));
        context.client().send(new ClientboundMasterPacket().ownerClientId(context.client().id()));
        //TODO: Hardcoded info needs to change
        context.client().send(new ClientboundRoomConfigPacket().mapId(gameData.mapId()).mapAlias(gameData.mapAlias).
                weaponOption(gameData.weaponOption).timeLimit(gameData.timelimit).killCount(0).canJoinMidGame(gameData.canJoinMidGame()).
                autoBalance(gameData.autoBalance()).allowBuildGun(false).password("").commented(0).roomType(roomType()).drop(gameData.drop).wanted(gameData.wanted));

        boolean[] slotLocks = room.getSlotLocksByMaxPlayers();
        for (int i = 0; i < slotLocks.length; i++) {
            context.client().send(
                    new ClientboundSlotLockPacket()
                            .index((byte) i)
                            .slotLocked(slotLocks[i])
            );
        }
        context.client().send(new ClientboundAddRoomPacket().roomInfo(room));
        context.client().send(new ClientboundCreateRoomPacket().roomInfo(room));
        //TODO: Hardcoded info needs to change
        context.client().send(new ClientboundEnterPacket().id(context.client().id()).nickname(context.client().name()).localIp(context.client().ip())
                .localPort(context.client().port()).remoteIp("127.0.0.1").remotePort(18890)
                .equipment(List.of("wau", "wax", "wba", "wap", "aac", "aad", "s07")).status(1).xp(9400000).clanId(0).clanName("Clan")
                .clanMark(0).rank(65).playerFlag(0).weaponChanges(List.of()).dropItems(List.of()));
        context.client().send(new ClientboundSlotInfoPacket().id(context.client().id()).slot((byte)0).status(ClientStatus.WAITING.id()).kill(0)
                .kill(0).death(0).assist(0).score(0).mission(0));
        //if (pkt.type() == RoomType.MAP_EDITOR) {
            //context.client().send(new ClientboundCopyrightPacket().ownerClientId(pkt.roomOwnerId()));
        //}

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
    public void handleTeamChange(Room room, NetContext<ServerboundResumeRoomPacket> context){
        room.status(RoomStatus.byId(context.packet().nextStatus()));
        context.client().send(new ClientboundUpdateRoomPacket().roomInfo(room));
    }

    @Override
    public void handleLeaveRoom(Room room, NetContext<ServerboundLeavePacket> context){
        context.client().send(new ClientboundLeavePacket().clientId(context.client().id()));
        context.client().send(new ClientboundSetStatusPacket().clientId(context.client().id()).status(ClientStatus.WAITING.id()));
        //if build mode different cleanup

        if (room.players() <= 0)
        {
            context.client().send(new ClientboundDelRoomPacket().roomId(room.id()));
            //msgRef.client.channel.RemoveMatch(matchData);
            return;
        }

        //if owner leaves room, give owner to next player
        //if (context.client().id() == owner)
        //{
        //    int owner = clientList[0].seq;
        //    context.client().send(new ClientboundMasterPacket().ownerClientId(owner));
        //}
    }

}
