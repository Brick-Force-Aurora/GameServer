package de.brickforceaurora.server.net.tcp.protocol.clientbound.original;

import de.brickforceaurora.server.net.tcp.protocol.IClientboundPacket;
import de.brickforceaurora.server.net.tcp.protocol.PacketBuf;

public final class ClientboundRoaminPacket implements IClientboundPacket {

    private int targetChannelId; //Target ChannelID

    public ClientboundRoaminPacket targetChannelId(final int targetChannelId) {
        this.targetChannelId = targetChannelId;
        return this;
    }

    public int targetChannelId() {
        return this.targetChannelId;
    }

    @Override
    public int packetId() {
        return 146;
    }

    @Override
    public void write(final PacketBuf buf) {
        buf.writeInt(this.targetChannelId);
    }
}