package de.brickforceaurora.server.net.tcp.protocol.clientbound.original;

import de.brickforceaurora.server.net.tcp.protocol.IClientboundPacket;
import de.brickforceaurora.server.net.tcp.protocol.PacketBuf;

public final class ClientboundStartingPacket implements IClientboundPacket {

    private int remain;

    public ClientboundStartingPacket remain(final int remain) {
        this.remain = remain;
        return this;
    }

    public int remain() {
        return this.remain;
    }

    @Override
    public int packetId() {
        return 390;
    }

    @Override
    public void write(final PacketBuf buf) {
        buf.writeInt(this.remain);
    }
}