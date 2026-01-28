package de.brickforceaurora.gameserver.net.protocol.serverbound;

import de.brickforceaurora.gameserver.net.protocol.IServerboundPacket;
import io.netty.buffer.ByteBuf;

public final class ServerboundMyMapListPacket implements IServerboundPacket {

    private int prevPage;
    private int nextPage;
    private int indexer;
    private int modeMask;

    public ServerboundMyMapListPacket prevPage(final int prevPage) {
        this.prevPage = prevPage;
        return this;
    }

    public int prevPage() {
        return this.prevPage;
    }

    public ServerboundMyMapListPacket nextPage(final int nextPage) {
        this.nextPage = nextPage;
        return this;
    }

    public int nextPage() {
        return this.nextPage;
    }

    public ServerboundMyMapListPacket indexer(final int indexer) {
        this.indexer = indexer;
        return this;
    }

    public int indexer() {
        return this.indexer;
    }

    public ServerboundMyMapListPacket modeMask(final int modeMask) {
        if (modeMask > 255L || modeMask < 0L) {
            throw new IllegalArgumentException("Value " + modeMask + " is out of bounds of allowed number range of 0 - 255");
        }
        this.modeMask = modeMask;
        return this;
    }

    public int modeMask() {
        return this.modeMask;
    }

    @Override
    public int packetId() {
        return 98;
    }

    @Override
    public void read(final ByteBuf buffer) {
        this.prevPage = buffer.readIntLE();
        this.nextPage = buffer.readIntLE();
        this.indexer = buffer.readIntLE();
        this.modeMask = buffer.readUnsignedByte();
    }
}