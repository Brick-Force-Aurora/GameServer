package de.brickforceaurora.server.net.tcp.protocol.serverbound.original;

import de.brickforceaurora.server.net.tcp.protocol.IServerboundPacket;
import de.brickforceaurora.server.net.tcp.protocol.PacketBuf;

public final class ServerboundNewMapListPacket implements IServerboundPacket {

    private int prevPage;
    private int nextPage;
    private int indexer;
    private int modeMask;
    private byte flag;
    private String filter;

    public ServerboundNewMapListPacket prevPage(final int prevPage) {
        this.prevPage = prevPage;
        return this;
    }

    public int prevPage() {
        return this.prevPage;
    }

    public ServerboundNewMapListPacket nextPage(final int nextPage) {
        this.nextPage = nextPage;
        return this;
    }

    public int nextPage() {
        return this.nextPage;
    }

    public ServerboundNewMapListPacket indexer(final int indexer) {
        this.indexer = indexer;
        return this;
    }

    public int indexer() {
        return this.indexer;
    }

    public ServerboundNewMapListPacket modeMask(final int modeMask) {
        if (modeMask > 255L || modeMask < 0L) {
            throw new IllegalArgumentException("Value " + modeMask + " is out of bounds of allowed number range of 0 - 255");
        }
        this.modeMask = modeMask;
        return this;
    }

    public int modeMask() {
        return this.modeMask;
    }

    public ServerboundNewMapListPacket flag(final byte flag) {
        this.flag = flag;
        return this;
    }

    public byte flag() {
        return this.flag;
    }

    public ServerboundNewMapListPacket filter(final String filter) {
        this.filter = filter;
        return this;
    }

    public String filter() {
        return this.filter;
    }

    @Override
    public int packetId() {
        return 56;
    }

    @Override
    public void read(final PacketBuf buf) {
        this.prevPage = buf.readInt();
        this.nextPage = buf.readInt();
        this.indexer = buf.readInt();
        this.modeMask = buf.readUnsignedByte();
        this.flag = buf.readByte();
        this.filter = buf.readString();
    }
}