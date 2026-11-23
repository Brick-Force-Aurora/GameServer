package de.brickforceaurora.server.net.tcp.protocol.serverbound.original;

import de.brickforceaurora.server.net.tcp.protocol.IServerboundPacket;
import de.brickforceaurora.server.net.tcp.protocol.PacketBuf;

public final class ServerboundAllMapPacket implements IServerboundPacket {

    private int prevPage;
    private int nextPage;
    private int indexer;
    private int modeMask;
    private int flag;
    private String filter;

    public ServerboundAllMapPacket prevPage(final int prevPage) {
        this.prevPage = prevPage;
        return this;
    }

    public int prevPage() {
        return this.prevPage;
    }

    public ServerboundAllMapPacket nextPage(final int nextPage) {
        this.nextPage = nextPage;
        return this;
    }

    public int nextPage() {
        return this.nextPage;
    }

    public ServerboundAllMapPacket indexer(final int indexer) {
        this.indexer = indexer;
        return this;
    }

    public int indexer() {
        return this.indexer;
    }

    public ServerboundAllMapPacket modeMask(final int modeMask) {
        if (modeMask > 65535L || modeMask < 0L) {
            throw new IllegalArgumentException("Value " + modeMask + " is out of bounds of allowed number range of 0 - 65535");
        }
        this.modeMask = modeMask;
        return this;
    }

    public int modeMask() {
        return this.modeMask;
    }

    public ServerboundAllMapPacket flag(final int flag) {
        this.flag = flag;
        return this;
    }

    public int flag() {
        return this.flag;
    }

    public ServerboundAllMapPacket filter(final String filter) {
        this.filter = filter;
        return this;
    }

    public String filter() {
        return this.filter;
    }

    @Override
    public int packetId() {
        return 431;
    }

    @Override
    public void read(final PacketBuf buf) {
        this.prevPage = buf.readInt();
        this.nextPage = buf.readInt();
        this.indexer = buf.readInt();
        this.modeMask = buf.readUnsignedShort();
        this.flag = buf.readInt();
        this.filter = buf.readString();
    }
}