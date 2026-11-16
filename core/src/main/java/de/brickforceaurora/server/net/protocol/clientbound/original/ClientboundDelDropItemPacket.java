package de.brickforceaurora.server.net.protocol.clientbound.original;

import de.brickforceaurora.server.net.protocol.IClientboundPacket;
import de.brickforceaurora.server.net.protocol.PacketBuf;

public final class ClientboundDelDropItemPacket implements IClientboundPacket {

    private int itemSeq;

    public ClientboundDelDropItemPacket itemSeq(final int itemSeq) {
        this.itemSeq = itemSeq;
        return this;
    }

    public int itemSeq() {
        return this.itemSeq;
    }

    @Override
    public int packetId() {
        return 530;
    }

    @Override
    public void write(final PacketBuf buf) {
        buf.writeInt(this.itemSeq);
    }
}
