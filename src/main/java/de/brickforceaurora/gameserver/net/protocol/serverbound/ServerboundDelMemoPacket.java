package de.brickforceaurora.gameserver.net.protocol.serverbound;

import de.brickforceaurora.gameserver.net.protocol.IServerboundPacket;
import io.netty.buffer.ByteBuf;

public final class ServerboundDelMemoPacket implements IServerboundPacket {

    private long seq;

    public ServerboundDelMemoPacket seq(final long seq) {
        this.seq = seq;
        return this;
    }

    public long seq() {
        return this.seq;
    }

    @Override
    public int packetId() {
        return 130;
    }

    @Override
    public void read(final ByteBuf buffer) {
        this.seq = buffer.readLongLE();
    }
}