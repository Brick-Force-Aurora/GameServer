package de.brickforceaurora.gameserver.net.protocol.serverbound;

import de.brickforceaurora.gameserver.net.protocol.IServerboundPacket;
import io.netty.buffer.ByteBuf;

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
    public void read(final ByteBuf buffer) {
        this.itemSeq = buffer.readIntLE();
    }
}