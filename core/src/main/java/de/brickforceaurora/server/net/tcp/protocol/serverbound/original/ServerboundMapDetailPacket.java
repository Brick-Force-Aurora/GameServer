package de.brickforceaurora.server.net.tcp.protocol.serverbound.original;

import de.brickforceaurora.server.net.tcp.protocol.IServerboundPacket;
import de.brickforceaurora.server.net.tcp.protocol.PacketBuf;

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
    public void read(final PacketBuf buf) {
        this.mapSeq = buf.readInt();
    }
}