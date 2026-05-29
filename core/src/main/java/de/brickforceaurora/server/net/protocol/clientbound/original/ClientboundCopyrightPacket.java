package de.brickforceaurora.server.net.protocol.clientbound.original;

import de.brickforceaurora.server.net.protocol.IClientboundPacket;
import de.brickforceaurora.server.net.protocol.PacketBuf;

public final class ClientboundCopyrightPacket implements IClientboundPacket {

	private int ownerClientId;
	private int userMapInfoSlot;

	public final ClientboundCopyrightPacket ownerClientId(int ownerClientId) {
		this.ownerClientId = ownerClientId;
		return this;
	}

	public final int ownerClientId() {
		return this.ownerClientId;
	}

	public final ClientboundCopyrightPacket userMapInfoSlot(int userMapInfoSlot) {
		this.userMapInfoSlot = userMapInfoSlot;
		return this;
	}

	public final int userMapInfoSlot() {
		return this.userMapInfoSlot;
	}

	@Override
	public int packetId() {
		return 53;
	}

	@Override
	public final void write(PacketBuf buf) {
		buf.writeInt(this.ownerClientId);
		buf.writeInt(this.userMapInfoSlot);
	}
}