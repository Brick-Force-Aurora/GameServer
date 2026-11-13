package de.brickforceaurora.server.net.protocol.clientbound.original;

import de.brickforceaurora.server.net.protocol.IClientboundPacket;
import de.brickforceaurora.server.net.protocol.PacketBuf;

public final class ClientboundDeathCountPacket implements IClientboundPacket {

	private int clientId;
	private int death;

	public final ClientboundDeathCountPacket clientId(int clientId) {
		this.clientId = clientId;
		return this;
	}

	public final int clientId() {
		return this.clientId;
	}

	public final ClientboundDeathCountPacket death(int death) {
		this.death = death;
		return this;
	}

	public final int death() {
		return this.death;
	}

	@Override
	public int packetId() {
		return 68;
	}

	@Override
	public final void write(PacketBuf buf) {
		buf.writeInt(this.clientId);
		buf.writeInt(this.death);
	}
}
