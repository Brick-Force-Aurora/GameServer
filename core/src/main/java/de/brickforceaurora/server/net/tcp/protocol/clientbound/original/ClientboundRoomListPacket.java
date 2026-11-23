package de.brickforceaurora.server.net.tcp.protocol.clientbound.original;

import de.brickforceaurora.server.net.tcp.protocol.IClientboundPacket;
import de.brickforceaurora.server.net.tcp.protocol.PacketBuf;
import de.brickforceaurora.server.net.tcp.protocol.data.api.IRoomInfo;

public final class ClientboundRoomListPacket implements IClientboundPacket {

    private IRoomInfo[] rooms;

    public ClientboundRoomListPacket rooms(final IRoomInfo[] rooms) {
        this.rooms = rooms;
        return this;
    }

    public IRoomInfo[] rooms() {
        return this.rooms;
    }

    @Override
    public int packetId() {
        return 468;
    }

    @Override
    public void write(final PacketBuf buf) {
        buf.writeArray(rooms, IRoomInfo::toBuffer);
    }
}