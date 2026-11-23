package de.brickforceaurora.server.net.tcp.protocol.clientbound.original;

import de.brickforceaurora.server.net.tcp.protocol.IClientboundPacket;
import de.brickforceaurora.server.net.tcp.protocol.PacketBuf;

public final class ClientboundEmptyTrainPacket implements IClientboundPacket {

    private int train;

    public ClientboundEmptyTrainPacket train(final int train) {
        this.train = train;
        return this;
    }

    public int train() {
        return this.train;
    }

    @Override
    public int packetId() {
        return 554;
    }

    @Override
    public void write(final PacketBuf buf) {
        buf.writeInt(this.train);
    }
}
