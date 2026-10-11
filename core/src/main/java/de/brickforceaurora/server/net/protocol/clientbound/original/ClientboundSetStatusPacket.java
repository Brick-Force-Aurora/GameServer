package de.brickforceaurora.server.net.protocol.clientbound.original;

import de.brickforceaurora.server.net.protocol.IClientboundPacket;
import de.brickforceaurora.server.net.protocol.PacketBuf;
import de.brickforceaurora.server.net.protocol.data.ClientStatus;

public final class ClientboundSetStatusPacket implements IClientboundPacket {

	private int clientId;
	private ClientStatus status;

	public final ClientboundSetStatusPacket clientId(int clientId) {
		this.clientId = clientId;
		return this;
	}

	public final int clientId() {
		return this.clientId;
	}

	public final ClientboundSetStatusPacket status(ClientStatus status) {
		this.status = status;
		return this;
	}

	public final ClientStatus status() {
		return this.status;
	}

	@Override
	public int packetId() {
		return 48;
	}

	@Override
	public final void write(PacketBuf buf) {
		buf.writeInt(this.clientId);
		buf.writeInt(this.status.id());
	}
}