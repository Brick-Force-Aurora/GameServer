package de.brickforceaurora.gameserver.net.protocol.serverbound;

import de.brickforceaurora.gameserver.net.protocol.IServerboundPacket;
import io.netty.buffer.ByteBuf;

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
    public void read(final ByteBuf buffer) {
        this.mapSeq = buffer.readIntLE();
        this.buyHow = buffer.readIntLE();
    }
}