package de.brickforceaurora.server.net.tcp.protocol.clientbound.original;

import de.brickforceaurora.server.net.tcp.protocol.IClientboundPacket;
import de.brickforceaurora.server.net.tcp.protocol.PacketBuf;

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
    public void write(final PacketBuf buf) {
        buf.writeByte(this.seed);
    }
}