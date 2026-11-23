package de.brickforceaurora.server.net.tcp.protocol.clientbound.original;

import de.brickforceaurora.server.net.tcp.protocol.IClientboundPacket;
import de.brickforceaurora.server.net.tcp.protocol.PacketBuf;

public final class ClientboundShooterToolPacket implements IClientboundPacket {

    private byte slot;
    private long item;

    public ClientboundShooterToolPacket slot(final byte slot) {
        this.slot = slot;
        return this;
    }

    public byte slot() {
        return this.slot;
    }

    public ClientboundShooterToolPacket item(final long item) {
        this.item = item;
        return this;
    }

    public long item() {
        return this.item;
    }

    @Override
    public int packetId() {
        return 332;
    }

    @Override
    public void write(final PacketBuf buf) {
        buf.writeByte(this.slot);
        buf.writeLong(this.item);
    }
}