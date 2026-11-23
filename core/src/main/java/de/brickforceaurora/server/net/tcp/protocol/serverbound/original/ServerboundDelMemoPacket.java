package de.brickforceaurora.server.net.tcp.protocol.serverbound.original;

import de.brickforceaurora.server.net.tcp.protocol.IServerboundPacket;
import de.brickforceaurora.server.net.tcp.protocol.PacketBuf;

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
    public void read(final PacketBuf buf) {
        this.seq = buf.readLong();
    }
}