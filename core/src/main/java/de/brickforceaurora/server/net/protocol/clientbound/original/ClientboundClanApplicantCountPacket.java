package de.brickforceaurora.server.net.protocol.clientbound.original;

import de.brickforceaurora.server.net.protocol.IClientboundPacket;
import de.brickforceaurora.server.net.protocol.PacketBuf;

public final class ClientboundClanApplicantCountPacket implements IClientboundPacket {

	private int val;

	public final ClientboundClanApplicantCountPacket val(int val) {
		this.val = val;
		return this;
	}

	public final int val() {
		return this.val;
	}

	@Override
	public int packetId() {
		return 559;
	}

	@Override
	public final void write(PacketBuf buf) {
		buf.writeInt(this.val);
	}
}