package de.brickforceaurora.server.net.protocol.clientbound.original;

import de.brickforceaurora.server.net.protocol.IClientboundPacket;
import de.brickforceaurora.server.net.protocol.PacketBuf;

public final class ClientboundLeavePacket implements IClientboundPacket {

    private int clientId;

    public ClientboundLeavePacket clientId(final int clientId) {
        this.clientId = clientId;
        return this;
    }

    public int clientId() {
        return this.clientId;
    }

    @Override
    public int packetId() {
        return 11;
    }

    @Override
    public void write(final PacketBuf buf) {
        buf.writeInt(this.clientId);
    }
}