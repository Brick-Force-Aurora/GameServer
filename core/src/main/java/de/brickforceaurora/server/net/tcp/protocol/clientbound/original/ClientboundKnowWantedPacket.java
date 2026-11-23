package de.brickforceaurora.server.net.tcp.protocol.clientbound.original;

import de.brickforceaurora.server.net.tcp.protocol.IClientboundPacket;
import de.brickforceaurora.server.net.tcp.protocol.PacketBuf;

public final class ClientboundKnowWantedPacket implements IClientboundPacket {

    private int[] clientIds;

    public ClientboundKnowWantedPacket clientIds(final int[] clientIds) {
        this.clientIds = clientIds;
        return this;
    }

    public int[] clientIds() {
        return this.clientIds;
    }

    @Override
    public int packetId() {
        return 543;
    }

    @Override
    public void write(final PacketBuf buf) {
        buf.writeInt(this.clientIds.length);
        for (final int clientId : this.clientIds) {
            buf.writeInt(clientId);
        }
    }
}
