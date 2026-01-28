package de.brickforceaurora.gameserver.net.protocol.clientbound;

import de.brickforceaurora.gameserver.net.protocol.IClientboundPacket;
import io.netty.buffer.ByteBuf;

public final class ClientboundRoaminPacket implements IClientboundPacket {

    private int channelDestinationId;

    public ClientboundRoaminPacket channelDestinationId(final int channelDestinationId) {
        this.channelDestinationId = channelDestinationId;
        return this;
    }

    public int channelDestinationId() {
        return this.channelDestinationId;
    }

    @Override
    public int packetId() {
        return 146;
    }

    @Override
    public void write(final ByteBuf buffer) {
        buffer.writeIntLE(this.channelDestinationId);
    }
}