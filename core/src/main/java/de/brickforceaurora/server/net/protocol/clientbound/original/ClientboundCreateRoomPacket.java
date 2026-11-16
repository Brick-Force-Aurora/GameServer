package de.brickforceaurora.server.net.protocol.clientbound.original;

import de.brickforceaurora.server.net.protocol.IClientboundPacket;
import de.brickforceaurora.server.net.protocol.PacketBuf;
import de.brickforceaurora.server.net.protocol.data.RoomType;
import de.brickforceaurora.server.net.protocol.data.api.IRoomInfo;

public final class ClientboundCreateRoomPacket implements IClientboundPacket {

    private RoomType roomType;
    private int id;
    private String title;

    public ClientboundCreateRoomPacket failure(final String message) {
        this.roomType = null;
        this.id = -1;
        this.title = message == null ? "" : message;
        return this;
    }

    public ClientboundCreateRoomPacket roomInfo(final IRoomInfo roomInfo) {
        this.roomType = roomInfo.type();
        this.id = roomInfo.id();
        this.title = roomInfo.title();
        return this;
    }

    @Override
    public int packetId() {
        return 8;
    }

    @Override
    public void write(final PacketBuf buf) {
        buf.writeInt(roomType == null ? -1 : roomType.id());
        buf.writeInt(id);
        buf.writeString(title);
    }
}