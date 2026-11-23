package de.brickforceaurora.server.net.tcp.protocol.serverbound.original;

import de.brickforceaurora.server.net.tcp.protocol.IServerboundPacket;
import de.brickforceaurora.server.net.tcp.protocol.PacketBuf;

public final class ServerboundMatchRestartCountPacket implements IServerboundPacket {

    private int count;

    public ServerboundMatchRestartCountPacket count(final int count) {
        this.count = count;
        return this;
    }

    public int count() {
        return this.count;
    }

    @Override
    public int packetId() {
        return 264;
    }

    @Override
    public void read(final PacketBuf buf) {
        this.count = buf.readInt();
    }
}