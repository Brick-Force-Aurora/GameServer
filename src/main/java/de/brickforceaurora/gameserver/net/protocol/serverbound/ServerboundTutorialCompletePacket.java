package de.brickforceaurora.gameserver.net.protocol.serverbound;

import de.brickforceaurora.gameserver.net.protocol.IServerboundPacket;
import io.netty.buffer.ByteBuf;

public final class ServerboundTutorialCompletePacket implements IServerboundPacket {

    private int endOf;

    public ServerboundTutorialCompletePacket endOf(final int endOf) {
        if (endOf > 255L || endOf < 0L) {
            throw new IllegalArgumentException("Value " + endOf + " is out of bounds of allowed number range of 0 - 255");
        }
        this.endOf = endOf;
        return this;
    }

    public int endOf() {
        return this.endOf;
    }

    @Override
    public int packetId() {
        return 170;
    }

    @Override
    public void read(final ByteBuf buffer) {
        this.endOf = buffer.readUnsignedByte();
    }
}