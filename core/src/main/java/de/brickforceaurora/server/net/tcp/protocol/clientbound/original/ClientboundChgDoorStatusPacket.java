package de.brickforceaurora.server.net.tcp.protocol.clientbound.original;

import de.brickforceaurora.server.net.tcp.protocol.IClientboundPacket;
import de.brickforceaurora.server.net.tcp.protocol.PacketBuf;

public final class ClientboundChgDoorStatusPacket implements IClientboundPacket {

    private int door;
    private byte status;

    public ClientboundChgDoorStatusPacket door(final int door) {
        this.door = door;
        return this;
    }

    public int door() {
        return this.door;
    }

    public ClientboundChgDoorStatusPacket status(final byte status) {
        this.status = status;
        return this;
    }

    public byte status() {
        return this.status;
    }

    @Override
    public int packetId() {
        return 449;
    }

    @Override
    public void write(final PacketBuf buf) {
        buf.writeInt(this.door);
        buf.writeByte(this.status);
    }
}
