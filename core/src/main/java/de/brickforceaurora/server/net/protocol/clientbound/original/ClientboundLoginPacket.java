package de.brickforceaurora.server.net.protocol.clientbound.original;

import de.brickforceaurora.server.net.protocol.IClientboundPacket;
import de.brickforceaurora.server.net.protocol.PacketBuf;

public final class ClientboundLoginPacket implements IClientboundPacket {

    private int clientId;
    private int channelId;

    public ClientboundLoginPacket clientId(final int clientId) {
        this.clientId = clientId;
        return this;
    }

    public int clientId() {
        return this.clientId;
    }

    public ClientboundLoginPacket channelId(final int channelId) {
        this.channelId = channelId;
        return this;
    }

    public int channelId() {
        return this.channelId;
    }

    @Override
    public int packetId() {
        return 2;
    }

    @Override
    public void write(final PacketBuf buf) {
        buf.writeInt(this.clientId);
        buf.writeInt(this.channelId);
    }
}