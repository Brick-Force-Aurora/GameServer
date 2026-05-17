package de.brickforceaurora.server.net.protocol.clientbound.original;

import de.brickforceaurora.server.net.protocol.IClientboundPacket;
import de.brickforceaurora.server.net.protocol.PacketBuf;

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
	public final void write(PacketBuf buf) {
		buf.writeInt(this.clientId);
		buf.writeInt(this.channelId);
	}
}