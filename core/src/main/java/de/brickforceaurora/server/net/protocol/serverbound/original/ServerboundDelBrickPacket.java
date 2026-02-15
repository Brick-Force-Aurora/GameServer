package de.brickforceaurora.server.net.protocol.serverbound.original;

import de.brickforceaurora.server.net.protocol.IServerboundPacket;
import de.brickforceaurora.server.net.protocol.PacketBuf;

public final class ServerboundDelBrickPacket implements IServerboundPacket {

	private int clientId;

	public final ServerboundDelBrickPacket clientId(int clientId) {
		this.clientId = clientId;
		return this;
	}

	public final int clientId() {
		return this.clientId;
	}

	@Override
	public int packetId() {
		return 15;
	}

	@Override
	public final void read(PacketBuf buf) {
		this.clientId = buf.readInt();
	}
}