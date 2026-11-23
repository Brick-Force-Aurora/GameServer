package de.brickforceaurora.server.net.tcp.protocol.clientbound.original;

import de.brickforceaurora.server.net.tcp.protocol.IClientboundPacket;
import de.brickforceaurora.server.net.tcp.protocol.PacketBuf;

public final class ClientboundMasterPacket implements IClientboundPacket {

    private int ownerClientId;

    public ClientboundMasterPacket ownerClientId(final int ownerClientId) {
        this.ownerClientId = ownerClientId;
        return this;
    }

    public int ownerClientId() {
        return this.ownerClientId;
    }

    @Override
    public int packetId() {
        return 31;
    }

    @Override
    public void write(final PacketBuf buf) {
        buf.writeInt(this.ownerClientId);
    }
}