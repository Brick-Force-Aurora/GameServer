package de.brickforceaurora.server.net.protocol.clientbound.original;

import de.brickforceaurora.server.net.protocol.IClientboundPacket;
import de.brickforceaurora.server.net.protocol.PacketBuf;

public final class ClientboundRoaminPacket implements IClientboundPacket {

	private int targetChannelId; //Target ChannelID

	public final ClientboundRoaminPacket targetChannelId(int targetChannelId) {
		this.targetChannelId = targetChannelId;
		return this;
	}

	public final int targetChannelId() {
		return this.targetChannelId;
	}

	@Override
	public int packetId() {
		return 146;
	}

	@Override
	public final void write(PacketBuf buf) {
		buf.writeInt(this.targetChannelId);
	}
}