package de.brickforceaurora.server.net.protocol.clientbound.original;

import de.brickforceaurora.server.net.protocol.IClientboundPacket;
import de.brickforceaurora.server.net.protocol.PacketBuf;
import de.brickforceaurora.server.net.protocol.data.api.IRoomInfo;

public final class ClientboundAddRoomPacket implements IClientboundPacket {

    private IRoomInfo roomInfo;

    public ClientboundAddRoomPacket roomInfo(final IRoomInfo roomInfo) {
        this.roomInfo = roomInfo;
        return this;
    }

    public IRoomInfo roomInfo() {
        return this.roomInfo;
    }

    @Override
    public int packetId() {
        return 5;
    }

    @Override
    public void write(final PacketBuf buf) {
        IRoomInfo.toBuffer(buf, roomInfo);
    }
}