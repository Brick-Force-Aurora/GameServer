package de.brickforceaurora.server.net.tcp.protocol.clientbound.original;

import de.brickforceaurora.server.net.tcp.protocol.IClientboundPacket;
import de.brickforceaurora.server.net.tcp.protocol.PacketBuf;

public final class ClientboundDeselectWantedPacket implements IClientboundPacket {

    private int clientId;

    public ClientboundDeselectWantedPacket clientId(final int clientId) {
        this.clientId = clientId;
        return this;
    }

    public int clientId() {
        return this.clientId;
    }

    @Override
    public int packetId() {
        return 544;
    }

    @Override
    public void write(final PacketBuf buf) {
        buf.writeInt(this.clientId);
    }
}
