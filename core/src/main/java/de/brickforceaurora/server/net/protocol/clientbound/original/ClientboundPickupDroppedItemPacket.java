package de.brickforceaurora.server.net.protocol.clientbound.original;

import de.brickforceaurora.server.net.protocol.IClientboundPacket;
import de.brickforceaurora.server.net.protocol.PacketBuf;

public final class ClientboundPickupDroppedItemPacket implements IClientboundPacket {

    private int clientId;
    private int itemSeq;

    public ClientboundPickupDroppedItemPacket clientId(final int clientId) {
        this.clientId = clientId;
        return this;
    }

    public int clientId() {
        return this.clientId;
    }

    public ClientboundPickupDroppedItemPacket itemSeq(final int itemSeq) {
        this.itemSeq = itemSeq;
        return this;
    }

    public int itemSeq() {
        return this.itemSeq;
    }

    @Override
    public int packetId() {
        return 529;
    }

    @Override
    public void write(final PacketBuf buf) {
        buf.writeInt(this.clientId);
        buf.writeInt(this.itemSeq);
    }
}
