package de.brickforceaurora.server.net.protocol.clientbound.original;

import de.brickforceaurora.server.net.protocol.IClientboundPacket;
import de.brickforceaurora.server.net.protocol.PacketBuf;

public final class ClientboundChatPacket implements IClientboundPacket {

	private int val;
	private int val2;
	private String val3;
	private String val4;
	private boolean val5;

	public final ClientboundChatPacket val(int val) {
		this.val = val;
		return this;
	}

	public final int val() {
		return this.val;
	}

	public final ClientboundChatPacket val2(int val2) {
		if (val2 > 255L || val2 < 0L) {
			throw new IllegalArgumentException(
					"Value " + val2 + " is out of bounds of allowed number range of 0 - 255");
		}
		this.val2 = val2;
		return this;
	}

	public final int val2() {
		return this.val2;
	}

	public final ClientboundChatPacket val3(String val3) {
		this.val3 = val3;
		return this;
	}

	public final String val3() {
		return this.val3;
	}

	public final ClientboundChatPacket val4(String val4) {
		this.val4 = val4;
		return this;
	}

	public final String val4() {
		return this.val4;
	}

	public final ClientboundChatPacket val5(boolean val5) {
		this.val5 = val5;
		return this;
	}

	public final boolean val5() {
		return this.val5;
	}

	@Override
	public int packetId() {
		return 25;
	}

	@Override
	public final void write(PacketBuf buf) {
		buf.writeInt(this.val);
		buf.writeByte(this.val2);
		buf.writeString(this.val3);
		buf.writeString(this.val4);
		buf.writeBoolean(this.val5);
	}
}