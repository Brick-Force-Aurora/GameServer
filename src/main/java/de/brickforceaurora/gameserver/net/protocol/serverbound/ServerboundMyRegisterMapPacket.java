package de.brickforceaurora.gameserver.net.protocol.serverbound;

import de.brickforceaurora.gameserver.net.protocol.IServerboundPacket;
import io.netty.buffer.ByteBuf;

public final class ServerboundMyRegisterMapPacket implements IServerboundPacket {

    private int prevPage;
    private int nextPage;
    private int indexer;
    private int modeMask;

    public ServerboundMyRegisterMapPacket prevPage(final int prevPage) {
        this.prevPage = prevPage;
        return this;
    }

    public int prevPage() {
        return this.prevPage;
    }

    public ServerboundMyRegisterMapPacket nextPage(final int nextPage) {
        this.nextPage = nextPage;
        return this;
    }

    public int nextPage() {
        return this.nextPage;
    }

    public ServerboundMyRegisterMapPacket indexer(final int indexer) {
        this.indexer = indexer;
        return this;
    }

    public int indexer() {
        return this.indexer;
    }

    public ServerboundMyRegisterMapPacket modeMask(final int modeMask) {
        if (modeMask > 32767L || modeMask < 0L) {
            throw new IllegalArgumentException("Value " + modeMask + " is out of bounds of allowed number range of 0 - 32767");
        }
        this.modeMask = modeMask;
        return this;
    }

    public int modeMask() {
        return this.modeMask;
    }

    @Override
    public int packetId() {
        return 427;
    }

    @Override
    public void read(final ByteBuf buffer) {
        this.prevPage = buffer.readIntLE();
        this.nextPage = buffer.readIntLE();
        this.indexer = buffer.readIntLE();
        this.modeMask = buffer.readUnsignedShortLE();
    }
}