package de.brickforceaurora.server.match.listener;

import de.brickforceaurora.server.match.MatchServerApp;
import de.brickforceaurora.server.match.room.Room;
import de.brickforceaurora.server.match.room.RoomManager;
import de.brickforceaurora.server.net.INetListener;
import de.brickforceaurora.server.net.NetContext;
import de.brickforceaurora.server.net.PacketHandler;
import de.brickforceaurora.server.net.protocol.clientbound.original.*;
import de.brickforceaurora.server.net.protocol.data.RoomInfo;
import de.brickforceaurora.server.net.protocol.data.RoomStatus;
import de.brickforceaurora.server.net.protocol.data.RoomType;
import de.brickforceaurora.server.net.protocol.serverbound.original.ServerboundCreateRoomPacket;
import de.brickforceaurora.server.net.protocol.serverbound.original.ServerboundRoomConfigPacket;
import de.brickforceaurora.server.net.protocol.serverbound.original.ServerboundRoomListPacket;
import me.lauriichan.snowframe.SnowFrame;
import me.lauriichan.snowframe.extension.Extension;

@Extension
public class RoomListener_ implements INetListener {

    private final RoomManager roomManager;

    public RoomListener_(final SnowFrame<MatchServerApp> snowFrame) {
        roomManager = snowFrame.app().roomManager();
    }

    @PacketHandler
    public void onRoomList(final NetContext<ServerboundRoomListPacket> context) {
        context.client().send(new ClientboundRoomListPacket().rooms(new RoomInfo[]{}));
    }

    @PacketHandler
    public void onRoomConfig(final NetContext<ServerboundRoomConfigPacket> context) {
        //update current room with the provided data
        //if bungee cache map
        //if bnd unpack timer and set times and repeat values, cache map as well, so init specifics
        context.client().send(new ClientboundRoomConfigPacket());
        context.client().send(new ClientboundUpdateRoomPacket());
    }

    @PacketHandler
    public void onRoomCreate(final NetContext<ServerboundCreateRoomPacket> context) {
        ServerboundCreateRoomPacket pckt = context.packet();
        Room room = roomManager.createRoom(pckt.type(), pckt.title(), pckt.isLocked(), pckt.password(), pckt.maxPlayers(), pckt.parameters());
        //add provided data
        //add client to room
        context.client().send(new ClientboundRendezvousInfoPacket().ip("").port(1));
        context.client().send(new ClientboundMasterPacket().ownerClientId(pckt.master()));
        //context.client().send(new ClientboundSlotLockPacket()); //for each slot in room send this packet
        context.client().send(new ClientboundRoomConfigPacket().roomInfo(room));
        context.client().send(new ClientboundAddRoomPacket().roomInfo(room));
        context.client().send(new ClientboundCreateRoomPacket().roomInfo(room));
        //context.client().send(new ClientboundEnterPacket());
        if (RoomType.byId(pckt.type()) == RoomType.MAP_EDITOR){
            context.client().send(new ClientboundCopyrightPacket().ownerClientId(pckt.master()));
        }
    }
}
