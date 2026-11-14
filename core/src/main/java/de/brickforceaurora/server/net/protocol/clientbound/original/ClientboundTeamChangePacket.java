package de.brickforceaurora.server.net.protocol.clientbound.original;

import de.brickforceaurora.server.net.protocol.IClientboundPacket;
import de.brickforceaurora.server.net.protocol.PacketBuf;

public final class ClientboundTeamChangePacket implements IClientboundPacket {

	private int clientId;
	private int previousSlot;
	private int slot;

	public final ClientboundTeamChangePacket clientId(int clientId) {
		this.clientId = clientId;
		return this;
	}

	public final int clientId() {
		return this.clientId;
	}

	public final ClientboundTeamChangePacket previousSlot(int previousSlot) {
		this.previousSlot = previousSlot;
		return this;
	}

	public final int previousSlot() {
		return this.previousSlot;
	}

	public final ClientboundTeamChangePacket slot(int slot) {
		this.slot = slot;
		return this;
	}

	public final int slot() {
		return this.slot;
	}

	@Override
	public int packetId() {
		return 81;
	}

	@Override
	public final void write(PacketBuf buf) {
		buf.writeInt(this.clientId);
		buf.writeInt(this.previousSlot);
		buf.writeInt(this.slot);
	}
}