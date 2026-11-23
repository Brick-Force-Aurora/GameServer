package de.brickforceaurora.server.net.tcp.protocol.serverbound.original;

import de.brickforceaurora.server.net.tcp.protocol.IServerboundPacket;
import de.brickforceaurora.server.net.tcp.protocol.PacketBuf;

public final class ServerboundMyDownloadMapPacket implements IServerboundPacket {

    private int prevPage;
    private int nextPage;
    private int indexer;
    private int modeMask;

    public ServerboundMyDownloadMapPacket prevPage(final int prevPage) {
        this.prevPage = prevPage;
        return this;
    }

    public int prevPage() {
        return this.prevPage;
    }

    public ServerboundMyDownloadMapPacket nextPage(final int nextPage) {
        this.nextPage = nextPage;
        return this;
    }

    public int nextPage() {
        return this.nextPage;
    }

    public ServerboundMyDownloadMapPacket indexer(final int indexer) {
        this.indexer = indexer;
        return this;
    }

    public int indexer() {
        return this.indexer;
    }

    public ServerboundMyDownloadMapPacket modeMask(final int modeMask) {
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
        return 425;
    }

    @Override
    public void read(final PacketBuf buf) {
        this.prevPage = buf.readInt();
        this.nextPage = buf.readInt();
        this.indexer = buf.readInt();
        this.modeMask = buf.readUnsignedShort();
    }
}