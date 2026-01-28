package de.brickforceaurora.gameserver.net.protocol.serverbound;

import de.brickforceaurora.gameserver.net.protocol.IServerboundPacket;
import io.netty.buffer.ByteBuf;

public final class ServerboundUnequipPacket implements IServerboundPacket {

    private long seq;

    public ServerboundUnequipPacket seq(final long seq) {
        this.seq = seq;
        return this;
    }

    public long seq() {
        return this.seq;
    }

    @Override
    public int packetId() {
        return 37;
    }

    @Override
    public void read(final ByteBuf buffer) {
        this.seq = buffer.readLongLE();
    }
}