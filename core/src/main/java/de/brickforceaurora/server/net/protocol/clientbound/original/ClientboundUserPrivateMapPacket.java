package de.brickforceaurora.server.net.protocol.clientbound.original;

import de.brickforceaurora.server.net.protocol.IClientboundPacket;
import de.brickforceaurora.server.net.protocol.PacketBuf;

public final class ClientboundUserPrivateMapPacket implements IClientboundPacket {

	private int val;
	private String val2;
	private int val3;
	private int val4;
	private byte val5;
	private byte val6;
	private byte val7;
	private byte val8;
	private byte val9;
	private byte val10;

	public final ClientboundUserPrivateMapPacket val(int val) {
		if (val > 255L || val < 0L) {
			throw new IllegalArgumentException("Value " + val + " is out of bounds of allowed number range of 0 - 255");
		}
		this.val = val;
		return this;
	}

	public final int val() {
		return this.val;
	}

	public final ClientboundUserPrivateMapPacket val2(String val2) {
		this.val2 = val2;
		return this;
	}

	public final String val2() {
		return this.val2;
	}

	public final ClientboundUserPrivateMapPacket val3(int val3) {
		this.val3 = val3;
		return this;
	}

	public final int val3() {
		return this.val3;
	}

	public final ClientboundUserPrivateMapPacket val4(int val4) {
		this.val4 = val4;
		return this;
	}

	public final int val4() {
		return this.val4;
	}

	public final ClientboundUserPrivateMapPacket val5(byte val5) {
		this.val5 = val5;
		return this;
	}

	public final byte val5() {
		return this.val5;
	}

	public final ClientboundUserPrivateMapPacket val6(byte val6) {
		this.val6 = val6;
		return this;
	}

	public final byte val6() {
		return this.val6;
	}

	public final ClientboundUserPrivateMapPacket val7(byte val7) {
		this.val7 = val7;
		return this;
	}

	public final byte val7() {
		return this.val7;
	}

	public final ClientboundUserPrivateMapPacket val8(byte val8) {
		this.val8 = val8;
		return this;
	}

	public final byte val8() {
		return this.val8;
	}

	public final ClientboundUserPrivateMapPacket val9(byte val9) {
		this.val9 = val9;
		return this;
	}

	public final byte val9() {
		return this.val9;
	}

	public final ClientboundUserPrivateMapPacket val10(byte val10) {
		this.val10 = val10;
		return this;
	}

	public final byte val10() {
		return this.val10;
	}

	@Override
	public int packetId() {
		return 41;
	}

	@Override
	public final void write(PacketBuf buf) {
		buf.writeByte(this.val);
		buf.writeString(this.val2);
		buf.writeInt(this.val3);
		buf.writeInt(this.val4);
		buf.writeByte(this.val5);
		buf.writeByte(this.val6);
		buf.writeByte(this.val7);
		buf.writeByte(this.val8);
		buf.writeByte(this.val9);
		buf.writeByte(this.val10);
	}
}