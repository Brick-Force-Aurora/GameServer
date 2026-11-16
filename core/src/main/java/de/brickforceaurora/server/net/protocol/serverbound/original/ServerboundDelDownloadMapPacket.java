package de.brickforceaurora.server.net.protocol.serverbound.original;

import de.brickforceaurora.server.net.protocol.IServerboundPacket;
import de.brickforceaurora.server.net.protocol.PacketBuf;

public final class ServerboundDelDownloadMapPacket implements IServerboundPacket {

    private int mapSeq;

    public ServerboundDelDownloadMapPacket mapSeq(final int mapSeq) {
        this.mapSeq = mapSeq;
        return this;
    }

    public int mapSeq() {
        return this.mapSeq;
    }

    @Override
    public int packetId() {
        return 176;
    }

    @Override
    public void read(final PacketBuf buf) {
        this.mapSeq = buf.readInt();
    }
}