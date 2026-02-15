package de.brickforceaurora.server.net.protocol.clientbound.original;

import de.brickforceaurora.server.net.protocol.IClientboundPacket;
import de.brickforceaurora.server.net.protocol.PacketBuf;

public final class ClientboundMemoPacket implements IClientboundPacket {

	private long val;
	private String val2;
	private String val3;
	private String val4;
	private String val5;
	private int val6;
	private int val7;
	private byte val8;
	private byte val9;
	private byte val10;
	private byte val11;

	public final ClientboundMemoPacket val(long val) {
		this.val = val;
		return this;
	}

	public final long val() {
		return this.val;
	}

	public final ClientboundMemoPacket val2(String val2) {
		this.val2 = val2;
		return this;
	}

	public final String val2() {
		return this.val2;
	}

	public final ClientboundMemoPacket val3(String val3) {
		this.val3 = val3;
		return this;
	}

	public final String val3() {
		return this.val3;
	}

	public final ClientboundMemoPacket val4(String val4) {
		this.val4 = val4;
		return this;
	}

	public final String val4() {
		return this.val4;
	}

	public final ClientboundMemoPacket val5(String val5) {
		this.val5 = val5;
		return this;
	}

	public final String val5() {
		return this.val5;
	}

	public final ClientboundMemoPacket val6(int val6) {
		this.val6 = val6;
		return this;
	}

	public final int val6() {
		return this.val6;
	}

	public final ClientboundMemoPacket val7(int val7) {
		this.val7 = val7;
		return this;
	}

	public final int val7() {
		return this.val7;
	}

	public final ClientboundMemoPacket val8(byte val8) {
		this.val8 = val8;
		return this;
	}

	public final byte val8() {
		return this.val8;
	}

	public final ClientboundMemoPacket val9(byte val9) {
		this.val9 = val9;
		return this;
	}

	public final byte val9() {
		return this.val9;
	}

	public final ClientboundMemoPacket val10(byte val10) {
		this.val10 = val10;
		return this;
	}

	public final byte val10() {
		return this.val10;
	}

	public final ClientboundMemoPacket val11(byte val11) {
		this.val11 = val11;
		return this;
	}

	public final byte val11() {
		return this.val11;
	}

	@Override
	public int packetId() {
		return 125;
	}

	@Override
	public final void write(PacketBuf buf) {
		buf.writeLong(this.val);
		buf.writeString(this.val2);
		buf.writeString(this.val3);
		buf.writeString(this.val4);
		buf.writeString(this.val5);
		buf.writeInt(this.val6);
		buf.writeInt(this.val7);
		buf.writeByte(this.val8);
		buf.writeByte(this.val9);
		buf.writeByte(this.val10);
		buf.writeByte(this.val11);
	}
}