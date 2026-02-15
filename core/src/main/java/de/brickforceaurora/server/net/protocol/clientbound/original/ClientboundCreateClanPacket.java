package de.brickforceaurora.server.net.protocol.clientbound.original;

import de.brickforceaurora.server.net.protocol.IClientboundPacket;
import de.brickforceaurora.server.net.protocol.PacketBuf;

public final class ClientboundCreateClanPacket implements IClientboundPacket {

	private int val;
	private String val2;

	public final ClientboundCreateClanPacket val(int val) {
		this.val = val;
		return this;
	}

	public final int val() {
		return this.val;
	}

	public final ClientboundCreateClanPacket val2(String val2) {
		this.val2 = val2;
		return this;
	}

	public final String val2() {
		return this.val2;
	}

	@Override
	public int packetId() {
		return 190;
	}

	@Override
	public final void write(PacketBuf buf) {
		buf.writeInt(this.val);
		buf.writeString(this.val2);
	}
}