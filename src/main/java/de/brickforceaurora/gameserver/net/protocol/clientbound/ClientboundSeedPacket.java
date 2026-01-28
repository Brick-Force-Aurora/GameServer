package de.brickforceaurora.gameserver.net.protocol.clientbound;

import de.brickforceaurora.gameserver.net.protocol.IClientboundPacket;
import io.netty.buffer.ByteBuf;

public final class ClientboundSeedPacket implements IClientboundPacket {

    private int seed;

    public ClientboundSeedPacket seed(final int seed) {
        if (seed > 255L || seed < 0L) {
            throw new IllegalArgumentException("Value " + seed + " is out of bounds of allowed number range of 0 - 255");
        }
        this.seed = seed;
        return this;
    }

    public int seed() {
        return this.seed;
    }

    @Override
    public int packetId() {
        return 84;
    }

    @Override
    public void write(final ByteBuf buffer) {
        buffer.writeByte(this.seed & 0xFF);
    }
}