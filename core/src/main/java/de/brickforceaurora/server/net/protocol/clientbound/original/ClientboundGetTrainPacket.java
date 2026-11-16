package de.brickforceaurora.server.net.protocol.clientbound.original;

import de.brickforceaurora.server.net.protocol.IClientboundPacket;
import de.brickforceaurora.server.net.protocol.PacketBuf;

public final class ClientboundGetTrainPacket implements IClientboundPacket {

    private int shooter;
    private int train;

    public ClientboundGetTrainPacket shooter(final int shooter) {
        this.shooter = shooter;
        return this;
    }

    public int shooter() {
        return this.shooter;
    }

    public ClientboundGetTrainPacket train(final int train) {
        this.train = train;
        return this;
    }

    public int train() {
        return this.train;
    }

    @Override
    public int packetId() {
        return 552;
    }

    @Override
    public void write(final PacketBuf buf) {
        buf.writeInt(this.shooter);
        buf.writeInt(this.train);
    }
}
