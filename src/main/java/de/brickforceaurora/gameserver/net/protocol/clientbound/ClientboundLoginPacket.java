package de.brickforceaurora.gameserver.net.protocol.clientbound;

import de.brickforceaurora.gameserver.net.protocol.IClientboundPacket;
import io.netty.buffer.ByteBuf;

public final class ClientboundLoginPacket implements IClientboundPacket {

    private int channelId;

    public ClientboundLoginPacket channelId(final int channelId) {
        this.channelId = channelId;
        return this;
    }

    public int channelId() {
        return this.channelId;
    }

    @Override
    public boolean requiresClientId() {
        return true;
    }

    @Override
    public int packetId() {
        return 2;
    }

    @Override
    public void write(final ByteBuf buffer) {
        buffer.writeIntLE(this.channelId);
    }
}