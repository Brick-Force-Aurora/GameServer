package de.brickforceaurora.gameserver.net.protocol.clientbound;

import de.brickforceaurora.gameserver.net.protocol.IClientboundPacket;
import io.netty.buffer.ByteBuf;

public final class ClientboundCurChannelPacket implements IClientboundPacket {

	private int channelId;

	public final ClientboundCurChannelPacket channelId(int channelId) {
		this.channelId = channelId;
		return this;
	}

	public final int channelId() {
		return this.channelId;
	}

	@Override
	public int packetId() {
		return 147;
	}

	@Override
	public final void write(ByteBuf buffer) {
		buffer.writeIntLE(this.channelId);
	}
}