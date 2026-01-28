package de.brickforceaurora.gameserver.net.protocol.serverbound;

import de.brickforceaurora.gameserver.net.protocol.IServerboundPacket;
import io.netty.buffer.ByteBuf;

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
    public void read(final ByteBuf buffer) {
        this.slot = buffer.readByte();
        this.tool = buffer.readLongLE();
    }
}