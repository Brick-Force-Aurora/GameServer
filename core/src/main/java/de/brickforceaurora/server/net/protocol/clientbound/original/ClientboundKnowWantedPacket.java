package de.brickforceaurora.server.net.protocol.clientbound.original;

import de.brickforceaurora.server.net.protocol.IClientboundPacket;
import de.brickforceaurora.server.net.protocol.PacketBuf;

public final class ClientboundKnowWantedPacket implements IClientboundPacket {

	private int[] clientIds;

	public final ClientboundKnowWantedPacket clientIds(int[] clientIds) {
		this.clientIds = clientIds;
		return this;
	}

	public final int[] clientIds() {
		return this.clientIds;
	}

	@Override
	public int packetId() {
		return 543;
	}

	@Override
	public final void write(PacketBuf buf) {
		buf.writeInt(this.clientIds.length);
		for (int clientId : this.clientIds) {
			buf.writeInt(clientId);
		}
	}
}
