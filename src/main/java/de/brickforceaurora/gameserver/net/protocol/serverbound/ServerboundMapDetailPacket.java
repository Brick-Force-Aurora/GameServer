package de.brickforceaurora.gameserver.net.protocol.serverbound;

import de.brickforceaurora.gameserver.net.protocol.IServerboundPacket;
import io.netty.buffer.ByteBuf;

public final class ServerboundMapDetailPacket implements IServerboundPacket {

    private int mapSeq;

    public ServerboundMapDetailPacket mapSeq(final int mapSeq) {
        this.mapSeq = mapSeq;
        return this;
    }

    public int mapSeq() {
        return this.mapSeq;
    }

    @Override
    public int packetId() {
        return 437;
    }

    @Override
    public void read(final ByteBuf buffer) {
        this.mapSeq = buffer.readIntLE();
    }
}