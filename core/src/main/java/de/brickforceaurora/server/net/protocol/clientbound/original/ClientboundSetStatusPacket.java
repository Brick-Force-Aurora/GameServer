package de.brickforceaurora.server.net.protocol.clientbound.original;

import de.brickforceaurora.server.net.protocol.IClientboundPacket;
import de.brickforceaurora.server.net.protocol.PacketBuf;
import de.brickforceaurora.server.net.protocol.data.ClientStatus;

public final class ClientboundSetStatusPacket implements IClientboundPacket {

    private int clientId;
    private ClientStatus status;

    public ClientboundSetStatusPacket clientId(final int clientId) {
        this.clientId = clientId;
        return this;
    }

    public int clientId() {
        return this.clientId;
    }

    public ClientboundSetStatusPacket status(final ClientStatus status) {
        this.status = status;
        return this;
    }

    public ClientStatus status() {
        return this.status;
    }

    @Override
    public int packetId() {
        return 48;
    }

    @Override
    public void write(final PacketBuf buf) {
        buf.writeInt(this.clientId);
        buf.writeInt(this.status.id());
    }
}