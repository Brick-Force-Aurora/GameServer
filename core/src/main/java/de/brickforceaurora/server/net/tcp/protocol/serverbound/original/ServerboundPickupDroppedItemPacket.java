package de.brickforceaurora.server.net.tcp.protocol.serverbound.original;

import de.brickforceaurora.server.net.tcp.protocol.IServerboundPacket;
import de.brickforceaurora.server.net.tcp.protocol.PacketBuf;

public final class ServerboundPickupDroppedItemPacket implements IServerboundPacket {

    private int itemSeq;

    public ServerboundPickupDroppedItemPacket itemSeq(final int itemSeq) {
        this.itemSeq = itemSeq;
        return this;
    }

    public int itemSeq() {
        return this.itemSeq;
    }

    @Override
    public int packetId() {
        return 528;
    }

    @Override
    public void read(final PacketBuf buf) {
        this.itemSeq = buf.readInt();
    }
}