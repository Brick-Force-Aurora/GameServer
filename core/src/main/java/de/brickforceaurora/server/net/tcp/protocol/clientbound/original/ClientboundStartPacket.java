package de.brickforceaurora.server.net.tcp.protocol.clientbound.original;

import de.brickforceaurora.server.net.tcp.protocol.IClientboundPacket;
import de.brickforceaurora.server.net.tcp.protocol.PacketBuf;

public final class ClientboundStartPacket implements IClientboundPacket {

    private int result;

    public ClientboundStartPacket result(final int result) {
        this.result = result;
        return this;
    }

    public int result() {
        return this.result;
    }

    @Override
    public int packetId() {
        return 50;
    }

    @Override
    public void write(final PacketBuf buf) {
        buf.writeInt(this.result);
    }
}