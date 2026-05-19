package de.brickforceaurora.server.match.listener;

import de.brickforceaurora.server.net.INetListener;
import de.brickforceaurora.server.net.NetContext;
import de.brickforceaurora.server.net.PacketHandler;
import de.brickforceaurora.server.net.protocol.clientbound.original.ClientboundRoomListPacket;
import de.brickforceaurora.server.net.protocol.data.RoomInfo;
import de.brickforceaurora.server.net.protocol.serverbound.original.ServerboundRoomListPacket;
import me.lauriichan.snowframe.extension.Extension;

@Extension
public class RoomListener_ implements INetListener {

    @PacketHandler
    public void onRoomList(final NetContext<ServerboundRoomListPacket> context) {
        context.client().send(new ClientboundRoomListPacket().rooms(new RoomInfo[]{}));
    }
}
