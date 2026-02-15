package de.brickforceaurora.server.net.protocol.clientbound.original;

import de.brickforceaurora.server.net.protocol.IClientboundPacket;
import de.brickforceaurora.server.net.protocol.PacketBuf;

public final class ClientboundNoticePacket implements IClientboundPacket {

	private String val;
	private byte val2;

	public final ClientboundNoticePacket val(String val) {
		this.val = val;
		return this;
	}

	public final String val() {
		return this.val;
	}

	public final ClientboundNoticePacket val2(byte val2) {
		this.val2 = val2;
		return this;
	}

	public final byte val2() {
		return this.val2;
	}

	@Override
	public int packetId() {
		return 346;
	}

	@Override
	public final void write(PacketBuf buf) {
		buf.writeString(this.val);
		buf.writeByte(this.val2);
	}
}