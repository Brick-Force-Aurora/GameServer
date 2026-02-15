package de.brickforceaurora.server.net.protocol.clientbound.original;

import de.brickforceaurora.server.net.protocol.IClientboundPacket;
import de.brickforceaurora.server.net.protocol.PacketBuf;

public final class ClientboundClanMarkChangedPacket implements IClientboundPacket {

	private int val;

	public final ClientboundClanMarkChangedPacket val(int val) {
		this.val = val;
		return this;
	}

	public final int val() {
		return this.val;
	}

	@Override
	public int packetId() {
		return 236;
	}

	@Override
	public final void write(PacketBuf buf) {
		buf.writeInt(this.val);
	}
}