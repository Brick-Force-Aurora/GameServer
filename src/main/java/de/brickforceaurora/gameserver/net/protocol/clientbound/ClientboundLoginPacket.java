package de.brickforceaurora.gameserver.net.protocol.clientbound;

import de.brickforceaurora.gameserver.net.protocol.IClientboundPacket;
import io.netty.buffer.ByteBuf;

public final class ClientboundLoginPacket implements IClientboundPacket {

	private int clientId;
	private int channelId;

	public final ClientboundLoginPacket clientId(int clientId) {
		this.clientId = clientId;
		return this;
	}

	public final int clientId() {
		return this.clientId;
	}

	public final ClientboundLoginPacket channelId(int channelId) {
		this.channelId = channelId;
		return this;
	}

	public final int channelId() {
		return this.channelId;
	}

	@Override
	public int packetId() {
		return 2;
	}

	@Override
	public final void write(ByteBuf buffer) {
		buffer.writeIntLE(this.clientId);
		buffer.writeIntLE(this.channelId);
	}
}