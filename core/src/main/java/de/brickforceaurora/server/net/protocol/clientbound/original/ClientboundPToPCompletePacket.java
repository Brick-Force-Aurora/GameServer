package de.brickforceaurora.server.net.protocol.clientbound.original;

import de.brickforceaurora.server.net.protocol.IClientboundPacket;
import de.brickforceaurora.server.net.protocol.PacketBuf;

public final class ClientboundPToPCompletePacket implements IClientboundPacket {

	private int clientId;

	public final ClientboundPToPCompletePacket clientId(int clientId) {
		this.clientId = clientId;
		return this;
	}

	public final int clientId() {
		return this.clientId;
	}

	@Override
	public int packetId() {
		return 136;
	}

	@Override
	public final void write(PacketBuf buf) {
		buf.writeInt(this.clientId);
	}
}