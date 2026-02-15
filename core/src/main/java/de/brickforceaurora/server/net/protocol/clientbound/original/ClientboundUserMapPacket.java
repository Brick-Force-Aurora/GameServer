package de.brickforceaurora.server.net.protocol.clientbound.original;

import de.brickforceaurora.server.net.protocol.IClientboundPacket;
import de.brickforceaurora.server.net.protocol.PacketBuf;

public final class ClientboundUserMapPacket implements IClientboundPacket {

	private int val;
	private int val2;
	private int val3;
	private String val4;
	private int val5;
	private int val6;
	private byte val7;
	private byte val8;
	private byte val9;
	private byte val10;
	private byte val11;
	private byte val12;

	public final ClientboundUserMapPacket val(int val) {
		this.val = val;
		return this;
	}

	public final int val() {
		return this.val;
	}

	public final ClientboundUserMapPacket val2(int val2) {
		this.val2 = val2;
		return this;
	}

	public final int val2() {
		return this.val2;
	}

	public final ClientboundUserMapPacket val3(int val3) {
		this.val3 = val3;
		return this;
	}

	public final int val3() {
		return this.val3;
	}

	public final ClientboundUserMapPacket val4(String val4) {
		this.val4 = val4;
		return this;
	}

	public final String val4() {
		return this.val4;
	}

	public final ClientboundUserMapPacket val5(int val5) {
		this.val5 = val5;
		return this;
	}

	public final int val5() {
		return this.val5;
	}

	public final ClientboundUserMapPacket val6(int val6) {
		this.val6 = val6;
		return this;
	}

	public final int val6() {
		return this.val6;
	}

	public final ClientboundUserMapPacket val7(byte val7) {
		this.val7 = val7;
		return this;
	}

	public final byte val7() {
		return this.val7;
	}

	public final ClientboundUserMapPacket val8(byte val8) {
		this.val8 = val8;
		return this;
	}

	public final byte val8() {
		return this.val8;
	}

	public final ClientboundUserMapPacket val9(byte val9) {
		this.val9 = val9;
		return this;
	}

	public final byte val9() {
		return this.val9;
	}

	public final ClientboundUserMapPacket val10(byte val10) {
		this.val10 = val10;
		return this;
	}

	public final byte val10() {
		return this.val10;
	}

	public final ClientboundUserMapPacket val11(byte val11) {
		this.val11 = val11;
		return this;
	}

	public final byte val11() {
		return this.val11;
	}

	public final ClientboundUserMapPacket val12(byte val12) {
		this.val12 = val12;
		return this;
	}

	public final byte val12() {
		return this.val12;
	}

	@Override
	public int packetId() {
		return 430;
	}

	@Override
	public final void write(PacketBuf buf) {
		buf.writeInt(this.val);
		buf.writeInt(this.val2);
		buf.writeInt(this.val3);
		buf.writeString(this.val4);
		buf.writeInt(this.val5);
		buf.writeInt(this.val6);
		buf.writeByte(this.val7);
		buf.writeByte(this.val8);
		buf.writeByte(this.val9);
		buf.writeByte(this.val10);
		buf.writeByte(this.val11);
		buf.writeByte(this.val12);
	}
}