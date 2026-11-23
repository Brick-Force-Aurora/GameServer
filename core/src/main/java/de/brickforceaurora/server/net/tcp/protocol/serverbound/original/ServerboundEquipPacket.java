package de.brickforceaurora.server.net.tcp.protocol.serverbound.original;

import de.brickforceaurora.server.net.tcp.protocol.IServerboundPacket;
import de.brickforceaurora.server.net.tcp.protocol.PacketBuf;

public final class ServerboundEquipPacket implements IServerboundPacket {

    private long seq;

    public ServerboundEquipPacket seq(final long seq) {
        this.seq = seq;
        return this;
    }

    public long seq() {
        return this.seq;
    }

    @Override
    public int packetId() {
        return 35;
    }

    @Override
    public void read(final PacketBuf buf) {
        this.seq = buf.readLong();
    }
}