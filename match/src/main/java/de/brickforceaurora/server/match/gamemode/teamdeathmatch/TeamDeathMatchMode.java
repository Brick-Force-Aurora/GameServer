package de.brickforceaurora.server.match.gamemode.teamdeathmatch;

import de.brickforceaurora.server.match.gamemode.GameData;
import de.brickforceaurora.server.match.gamemode.TeamGameMode;
import de.brickforceaurora.server.match.room.Room;
import de.brickforceaurora.server.net.NetContext;
import de.brickforceaurora.server.net.protocol.clientbound.original.ClientboundAddRoomPacket;
import de.brickforceaurora.server.net.protocol.clientbound.original.ClientboundCopyrightPacket;
import de.brickforceaurora.server.net.protocol.clientbound.original.ClientboundCreateRoomPacket;
import de.brickforceaurora.server.net.protocol.clientbound.original.ClientboundMasterPacket;
import de.brickforceaurora.server.net.protocol.clientbound.original.ClientboundRendezvousInfoPacket;
import de.brickforceaurora.server.net.protocol.clientbound.original.ClientboundRoomConfigPacket;
import de.brickforceaurora.server.net.protocol.clientbound.original.ClientboundUpdateRoomPacket;
import de.brickforceaurora.server.net.protocol.data.RoomType;
import de.brickforceaurora.server.net.protocol.serverbound.original.ServerboundCreateRoomPacket;
import de.brickforceaurora.server.net.protocol.serverbound.original.ServerboundRoomConfigPacket;
import me.lauriichan.snowframe.SnowFrame;
import me.lauriichan.snowframe.extension.Extension;

@Extension
public class TeamDeathMatchMode extends TeamGameMode<TDMData> {

    public TeamDeathMatchMode(final SnowFrame<?> snowFrame) {
        super(snowFrame, TDMData.class, RoomType.TEAM_MATCH);
    }

    @Override
    public GameData createGameDataFor(Room room) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public void handleRoomCreation(Room room, NetContext<ServerboundCreateRoomPacket> context) {
        ServerboundCreateRoomPacket pkt = context.packet();

        //add provided data
        //add client to room
        context.client().send(new ClientboundRendezvousInfoPacket().ip("").port(1));
        context.client().send(new ClientboundMasterPacket().ownerClientId(pkt.roomOwnerId()));
        //context.client().send(new ClientboundSlotLockPacket()); //for each slot in room send this packet
        //        context.client().send(new ClientboundRoomConfigPacket().roomInfo(room));
        context.client().send(new ClientboundAddRoomPacket().roomInfo(room));
        context.client().send(new ClientboundCreateRoomPacket().roomInfo(room));
        //context.client().send(new ClientboundEnterPacket());
        if (pkt.type() == RoomType.MAP_EDITOR) {
            context.client().send(new ClientboundCopyrightPacket().ownerClientId(pkt.roomOwnerId()));
        }

    }

    @Override
    public void handleRoomUpdate(Room room, NetContext<ServerboundRoomConfigPacket> context) {

        //update current room with the provided data
        //if bungee cache map
        //if bnd unpack timer and set times and repeat values, cache map as well, so init specifics
        context.client().send(new ClientboundRoomConfigPacket());
        context.client().send(new ClientboundUpdateRoomPacket());
    }

}
