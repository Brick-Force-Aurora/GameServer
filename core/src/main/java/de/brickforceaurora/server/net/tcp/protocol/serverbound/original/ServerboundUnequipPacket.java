package de.brickforceaurora.server.net.tcp.protocol.serverbound.original;

import de.brickforceaurora.server.net.tcp.protocol.IServerboundPacket;
import de.brickforceaurora.server.net.tcp.protocol.PacketBuf;

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
    public void read(final PacketBuf buf) {
        this.seq = buf.readLong();
    }
}