package de.brickforceaurora.server.net.protocol.clientbound.original;

import de.brickforceaurora.server.net.protocol.IClientboundPacket;
import de.brickforceaurora.server.net.protocol.PacketBuf;

public final class ClientboundItemPacket implements IClientboundPacket {

	private long val;
	private String val2;
	private byte val3;
	private int val4;
	private byte val5;
	private int val6;

	public final ClientboundItemPacket val(long val) {
		this.val = val;
		return this;
	}

	public final long val() {
		return this.val;
	}

	public final ClientboundItemPacket val2(String val2) {
		this.val2 = val2;
		return this;
	}

	public final String val2() {
		return this.val2;
	}

	public final ClientboundItemPacket val3(byte val3) {
		this.val3 = val3;
		return this;
	}

	public final byte val3() {
		return this.val3;
	}

	public final ClientboundItemPacket val4(int val4) {
		this.val4 = val4;
		return this;
	}

	public final int val4() {
		return this.val4;
	}

	public final ClientboundItemPacket val5(byte val5) {
		this.val5 = val5;
		return this;
	}

	public final byte val5() {
		return this.val5;
	}

	public final ClientboundItemPacket val6(int val6) {
		this.val6 = val6;
		return this;
	}

	public final int val6() {
		return this.val6;
	}

	@Override
	public int packetId() {
		return 34;
	}

	@Override
	public final void write(PacketBuf buf) {
		buf.writeLong(this.val);
		buf.writeString(this.val2);
		buf.writeByte(this.val3);
		buf.writeInt(this.val4);
		buf.writeByte(this.val5);
		buf.writeInt(this.val6);
	}
}