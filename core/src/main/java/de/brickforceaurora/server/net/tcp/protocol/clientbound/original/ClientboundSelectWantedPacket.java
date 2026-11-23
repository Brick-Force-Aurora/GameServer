package de.brickforceaurora.server.net.tcp.protocol.clientbound.original;

import de.brickforceaurora.server.net.tcp.protocol.IClientboundPacket;
import de.brickforceaurora.server.net.tcp.protocol.PacketBuf;

public final class ClientboundSelectWantedPacket implements IClientboundPacket {

    private int clientId;

    public ClientboundSelectWantedPacket clientId(final int clientId) {
        this.clientId = clientId;
        return this;
    }

    public int clientId() {
        return this.clientId;
    }

    @Override
    public int packetId() {
        return 542;
    }

    @Override
    public void write(final PacketBuf buf) {
        buf.writeInt(this.clientId);
    }
}
