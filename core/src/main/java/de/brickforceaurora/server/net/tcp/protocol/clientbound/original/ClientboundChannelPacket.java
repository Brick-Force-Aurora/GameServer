package de.brickforceaurora.server.net.tcp.protocol.clientbound.original;

import de.brickforceaurora.server.net.tcp.protocol.IClientboundPacket;
import de.brickforceaurora.server.net.tcp.protocol.PacketBuf;
import de.brickforceaurora.server.net.tcp.protocol.data.api.IChannelInfo;

public final class ClientboundChannelPacket implements IClientboundPacket {

    private IChannelInfo[] channels;

    public ClientboundChannelPacket channels(final IChannelInfo[] channels) {
        this.channels = channels;
        return this;
    }

    public IChannelInfo[] channels() {
        return this.channels;
    }

    @Override
    public int packetId() {
        return 141;
    }

    @Override
    public void write(final PacketBuf buf) {
        buf.writeArray(channels, IChannelInfo::toBuffer);
    }
}