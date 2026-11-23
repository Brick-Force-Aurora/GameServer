package de.brickforceaurora.server.net.tcp.protocol.serverbound.original;

import de.brickforceaurora.server.net.tcp.protocol.IServerboundPacket;
import de.brickforceaurora.server.net.tcp.protocol.PacketBuf;

public final class ServerboundWeeklyChartPacket implements IServerboundPacket {

    private int prevPage;
    private int nextPage;
    private int indexer;

    public ServerboundWeeklyChartPacket prevPage(final int prevPage) {
        this.prevPage = prevPage;
        return this;
    }

    public int prevPage() {
        return this.prevPage;
    }

    public ServerboundWeeklyChartPacket nextPage(final int nextPage) {
        this.nextPage = nextPage;
        return this;
    }

    public int nextPage() {
        return this.nextPage;
    }

    public ServerboundWeeklyChartPacket indexer(final int indexer) {
        this.indexer = indexer;
        return this;
    }

    public int indexer() {
        return this.indexer;
    }

    @Override
    public int packetId() {
        return 433;
    }

    @Override
    public void read(final PacketBuf buf) {
        this.prevPage = buf.readInt();
        this.nextPage = buf.readInt();
        this.indexer = buf.readInt();
    }
}