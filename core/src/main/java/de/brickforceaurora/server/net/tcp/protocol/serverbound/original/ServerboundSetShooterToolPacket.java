package de.brickforceaurora.server.net.tcp.protocol.serverbound.original;

import de.brickforceaurora.server.net.tcp.protocol.IServerboundPacket;
import de.brickforceaurora.server.net.tcp.protocol.PacketBuf;

public final class ServerboundSetShooterToolPacket implements IServerboundPacket {

    private byte slot;
    private long tool;

    public ServerboundSetShooterToolPacket slot(final byte slot) {
        this.slot = slot;
        return this;
    }

    public byte slot() {
        return this.slot;
    }

    public ServerboundSetShooterToolPacket tool(final long tool) {
        this.tool = tool;
        return this;
    }

    public long tool() {
        return this.tool;
    }

    @Override
    public int packetId() {
        return 333;
    }

    @Override
    public void read(final PacketBuf buf) {
        this.slot = buf.readByte();
        this.tool = buf.readLong();
    }
}