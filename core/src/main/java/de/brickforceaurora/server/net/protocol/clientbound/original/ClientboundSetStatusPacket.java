package de.brickforceaurora.server.net.protocol.clientbound.original;

import de.brickforceaurora.server.net.protocol.IClientboundPacket;
import de.brickforceaurora.server.net.protocol.PacketBuf;

public final class ClientboundSetStatusPacket implements IClientboundPacket {

	private int clientId;
	private int status;

	public final ClientboundSetStatusPacket clientId(int clientId) {
		this.clientId = clientId;
		return this;
	}

	public final int clientId() {
		return this.clientId;
	}

	public final ClientboundSetStatusPacket status(int status) {
		this.status = status;
		return this;
	}

	public final int status() {
		return this.status;
	}

	@Override
	public int packetId() {
		return 48;
	}

	@Override
	public final void write(PacketBuf buf) {
		buf.writeInt(this.clientId);
		buf.writeInt(this.status);
	}
}