package de.brickforceaurora.server.net.protocol.clientbound.original;

import de.brickforceaurora.server.net.protocol.IClientboundPacket;
import de.brickforceaurora.server.net.protocol.PacketBuf;

public final class ClientboundResultDonePacket implements IClientboundPacket {

	private int clientId;

	public final ClientboundResultDonePacket clientId(int clientId) {
		this.clientId = clientId;
		return this;
	}

	public final int clientId() {
		return this.clientId;
	}

	@Override
	public int packetId() {
		return 138;
	}

	@Override
	public final void write(PacketBuf buf) {
		buf.writeInt(this.clientId);
	}
}
