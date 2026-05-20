package de.brickforceaurora.server.net.protocol.clientbound.original;

import de.brickforceaurora.server.net.protocol.IClientboundPacket;
import de.brickforceaurora.server.net.protocol.PacketBuf;
import de.brickforceaurora.server.net.protocol.data.api.IChannelInfo;

public final class ClientboundChannelPacket implements IClientboundPacket {

	private IChannelInfo[] channels;

	public final ClientboundChannelPacket channels(IChannelInfo[] channels) {
		this.channels = channels;
		return this;
	}

	public final IChannelInfo[] channels() {
		return this.channels;
	}

	@Override
	public int packetId() {
		return 141;
	}

	@Override
	public final void write(PacketBuf buf) {
	    buf.writeArray(channels, IChannelInfo::toBuffer);
	}
}