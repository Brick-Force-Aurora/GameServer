package de.brickforceaurora.server.net.protocol.clientbound.original;

import de.brickforceaurora.server.net.protocol.IClientboundPacket;
import de.brickforceaurora.server.net.protocol.PacketBuf;

public final class ClientboundBuyItemPacket implements IClientboundPacket {

	private long val;
	private String val2;
	private int val3;
	private byte val4;
	private int val5;

	public final ClientboundBuyItemPacket val(long val) {
		this.val = val;
		return this;
	}

	public final long val() {
		return this.val;
	}

	public final ClientboundBuyItemPacket val2(String val2) {
		this.val2 = val2;
		return this;
	}

	public final String val2() {
		return this.val2;
	}

	public final ClientboundBuyItemPacket val3(int val3) {
		this.val3 = val3;
		return this;
	}

	public final int val3() {
		return this.val3;
	}

	public final ClientboundBuyItemPacket val4(byte val4) {
		this.val4 = val4;
		return this;
	}

	public final byte val4() {
		return this.val4;
	}

	public final ClientboundBuyItemPacket val5(int val5) {
		this.val5 = val5;
		return this;
	}

	public final int val5() {
		return this.val5;
	}

	@Override
	public int packetId() {
		return 122;
	}

	@Override
	public final void write(PacketBuf buf) {
		buf.writeLong(this.val);
		buf.writeString(this.val2);
		buf.writeInt(this.val3);
		buf.writeByte(this.val4);
		buf.writeInt(this.val5);
	}
}