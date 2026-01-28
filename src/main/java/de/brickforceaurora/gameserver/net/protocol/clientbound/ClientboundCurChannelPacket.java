package de.brickforceaurora.gameserver.net.protocol.clientbound;

import de.brickforceaurora.gameserver.net.protocol.IClientboundPacket;
import io.netty.buffer.ByteBuf;

public final class ClientboundCurChannelPacket implements IClientboundPacket {

    private int channelId;

    public ClientboundCurChannelPacket channelId(final int channelId) {
        this.channelId = channelId;
        return this;
    }

    public int channelId() {
        return this.channelId;
    }

    @Override
    public int packetId() {
        return 147;
    }

    @Override
    public void write(final ByteBuf buffer) {
        buffer.writeIntLE(this.channelId);
    }
}