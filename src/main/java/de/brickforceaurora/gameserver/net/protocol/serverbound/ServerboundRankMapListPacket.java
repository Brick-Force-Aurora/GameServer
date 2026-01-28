package de.brickforceaurora.gameserver.net.protocol.serverbound;

import de.brickforceaurora.gameserver.net.protocol.IServerboundPacket;
import io.netty.buffer.ByteBuf;

public final class ServerboundRankMapListPacket implements IServerboundPacket {

    private int prevPage;
    private int nextPage;
    private int indexer;
    private int modeMask;

    public ServerboundRankMapListPacket prevPage(final int prevPage) {
        this.prevPage = prevPage;
        return this;
    }

    public int prevPage() {
        return this.prevPage;
    }

    public ServerboundRankMapListPacket nextPage(final int nextPage) {
        this.nextPage = nextPage;
        return this;
    }

    public int nextPage() {
        return this.nextPage;
    }

    public ServerboundRankMapListPacket indexer(final int indexer) {
        this.indexer = indexer;
        return this;
    }

    public int indexer() {
        return this.indexer;
    }

    public ServerboundRankMapListPacket modeMask(final int modeMask) {
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
        return 58;
    }

    @Override
    public void read(final ByteBuf buffer) {
        this.prevPage = buffer.readIntLE();
        this.nextPage = buffer.readIntLE();
        this.indexer = buffer.readIntLE();
        this.modeMask = buffer.readUnsignedByte();
    }
}