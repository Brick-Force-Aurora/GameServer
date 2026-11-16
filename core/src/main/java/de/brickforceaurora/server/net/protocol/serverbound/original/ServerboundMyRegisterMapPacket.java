package de.brickforceaurora.server.net.protocol.serverbound.original;

import de.brickforceaurora.server.net.protocol.IServerboundPacket;
import de.brickforceaurora.server.net.protocol.PacketBuf;

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
        if (modeMask > 65535L || modeMask < 0L) {
            throw new IllegalArgumentException("Value " + modeMask + " is out of bounds of allowed number range of 0 - 65535");
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
    public void read(final PacketBuf buf) {
        this.prevPage = buf.readInt();
        this.nextPage = buf.readInt();
        this.indexer = buf.readInt();
        this.modeMask = buf.readUnsignedShort();
    }
}