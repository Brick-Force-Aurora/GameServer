package de.brickforceaurora.server.net.protocol.clientbound.original;

import de.brickforceaurora.server.net.protocol.IClientboundPacket;
import de.brickforceaurora.server.net.protocol.PacketBuf;

public final class ClientboundAdPacket implements IClientboundPacket {

	private int val;
	private int val2;
	private String val3;
	private int val4;
	private String val5;

	public final ClientboundAdPacket val(int val) {
		this.val = val;
		return this;
	}

	public final int val() {
		return this.val;
	}

	public final ClientboundAdPacket val2(int val2) {
		this.val2 = val2;
		return this;
	}

	public final int val2() {
		return this.val2;
	}

	public final ClientboundAdPacket val3(String val3) {
		this.val3 = val3;
		return this;
	}

	public final String val3() {
		return this.val3;
	}

	public final ClientboundAdPacket val4(int val4) {
		this.val4 = val4;
		return this;
	}

	public final int val4() {
		return this.val4;
	}

	public final ClientboundAdPacket val5(String val5) {
		this.val5 = val5;
		return this;
	}

	public final String val5() {
		return this.val5;
	}

	@Override
	public int packetId() {
		return 347;
	}

	@Override
	public final void write(PacketBuf buf) {
		buf.writeInt(this.val);
		buf.writeInt(this.val2);
		buf.writeString(this.val3);
		buf.writeInt(this.val4);
		buf.writeString(this.val5);
	}
}