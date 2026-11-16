package de.brickforceaurora.server.net.protocol.serverbound.original;

import de.brickforceaurora.server.net.protocol.IServerboundPacket;
import de.brickforceaurora.server.net.protocol.PacketBuf;

public final class ServerboundDownloadMapPacket implements IServerboundPacket {

    private int mapSeq;
    private int buyHow;

    public ServerboundDownloadMapPacket mapSeq(final int mapSeq) {
        this.mapSeq = mapSeq;
        return this;
    }

    public int mapSeq() {
        return this.mapSeq;
    }

    public ServerboundDownloadMapPacket buyHow(final int buyHow) {
        this.buyHow = buyHow;
        return this;
    }

    public int buyHow() {
        return this.buyHow;
    }

    @Override
    public int packetId() {
        return 174;
    }

    @Override
    public void read(final PacketBuf buf) {
        this.mapSeq = buf.readInt();
        this.buyHow = buf.readInt();
    }
}