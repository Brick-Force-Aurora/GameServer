package de.brickforceaurora.gameserver.net.protocol.serverbound;

import de.brickforceaurora.gameserver.net.protocol.IServerboundPacket;
import io.netty.buffer.ByteBuf;

public final class ServerboundMapDayPacket implements IServerboundPacket {

    private int prevPage;
    private int nextPage;
    private int indexer;

    public ServerboundMapDayPacket prevPage(final int prevPage) {
        this.prevPage = prevPage;
        return this;
    }

    public int prevPage() {
        return this.prevPage;
    }

    public ServerboundMapDayPacket nextPage(final int nextPage) {
        this.nextPage = nextPage;
        return this;
    }

    public int nextPage() {
        return this.nextPage;
    }

    public ServerboundMapDayPacket indexer(final int indexer) {
        this.indexer = indexer;
        return this;
    }

    public int indexer() {
        return this.indexer;
    }

    @Override
    public int packetId() {
        return 445;
    }

    @Override
    public void read(final ByteBuf buffer) {
        this.prevPage = buffer.readIntLE();
        this.nextPage = buffer.readIntLE();
        this.indexer = buffer.readIntLE();
    }
}