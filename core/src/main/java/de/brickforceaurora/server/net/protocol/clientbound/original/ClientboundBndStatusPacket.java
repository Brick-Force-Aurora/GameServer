package de.brickforceaurora.server.net.protocol.clientbound.original;

import de.brickforceaurora.server.net.protocol.IClientboundPacket;
import de.brickforceaurora.server.net.protocol.PacketBuf;

public final class ClientboundBndStatusPacket implements IClientboundPacket {

	private boolean val;

	public final ClientboundBndStatusPacket val(boolean val) {
		this.val = val;
		return this;
	}

	public final boolean val() {
		return this.val;
	}

	@Override
	public int packetId() {
		return 506;
	}

	@Override
	public final void write(PacketBuf buf) {
		buf.writeBoolean(this.val);
	}
}