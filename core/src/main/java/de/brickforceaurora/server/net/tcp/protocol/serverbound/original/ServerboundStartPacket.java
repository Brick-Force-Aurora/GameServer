package de.brickforceaurora.server.net.tcp.protocol.serverbound.original;

import de.brickforceaurora.server.net.tcp.protocol.IServerboundPacket;
import de.brickforceaurora.server.net.tcp.protocol.PacketBuf;

public final class ServerboundStartPacket implements IServerboundPacket {

    private int remain;

    public ServerboundStartPacket remain(final int remain) {
        this.remain = remain;
        return this;
    }

    public int remain() {
        return this.remain;
    }

    @Override
    public int packetId() {
        return 49;
    }

    @Override
    public void read(final PacketBuf buf) {
        this.remain = buf.readInt();
    }
}