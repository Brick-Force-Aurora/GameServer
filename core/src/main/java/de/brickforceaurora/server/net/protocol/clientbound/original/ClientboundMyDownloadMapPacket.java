package de.brickforceaurora.server.net.protocol.clientbound.original;

import de.brickforceaurora.server.net.protocol.IClientboundPacket;
import de.brickforceaurora.server.net.protocol.PacketBuf;

public final class ClientboundMyDownloadMapPacket implements IClientboundPacket {

	private int val;
	private int val2;
	private int val3;
	private String val4;
	private String val5;
	private int val6;
	private int val7;
	private int val8;
	private int val9;
	private byte val10;
	private byte val11;
	private byte val12;
	private byte val13;
	private byte val14;
	private int val15;
	private int val16;
	private int val17;
	private int val18;
	private int val19;
	private int val20;

	public final ClientboundMyDownloadMapPacket val(int val) {
		this.val = val;
		return this;
	}

	public final int val() {
		return this.val;
	}

	public final ClientboundMyDownloadMapPacket val2(int val2) {
		this.val2 = val2;
		return this;
	}

	public final int val2() {
		return this.val2;
	}

	public final ClientboundMyDownloadMapPacket val3(int val3) {
		this.val3 = val3;
		return this;
	}

	public final int val3() {
		return this.val3;
	}

	public final ClientboundMyDownloadMapPacket val4(String val4) {
		this.val4 = val4;
		return this;
	}

	public final String val4() {
		return this.val4;
	}

	public final ClientboundMyDownloadMapPacket val5(String val5) {
		this.val5 = val5;
		return this;
	}

	public final String val5() {
		return this.val5;
	}

	public final ClientboundMyDownloadMapPacket val6(int val6) {
		if (val6 > 65535L || val6 < 0L) {
			throw new IllegalArgumentException(
					"Value " + val6 + " is out of bounds of allowed number range of 0 - 65535");
		}
		this.val6 = val6;
		return this;
	}

	public final int val6() {
		return this.val6;
	}

	public final ClientboundMyDownloadMapPacket val7(int val7) {
		if (val7 > 255L || val7 < 0L) {
			throw new IllegalArgumentException(
					"Value " + val7 + " is out of bounds of allowed number range of 0 - 255");
		}
		this.val7 = val7;
		return this;
	}

	public final int val7() {
		return this.val7;
	}

	public final ClientboundMyDownloadMapPacket val8(int val8) {
		if (val8 > 255L || val8 < 0L) {
			throw new IllegalArgumentException(
					"Value " + val8 + " is out of bounds of allowed number range of 0 - 255");
		}
		this.val8 = val8;
		return this;
	}

	public final int val8() {
		return this.val8;
	}

	public final ClientboundMyDownloadMapPacket val9(int val9) {
		this.val9 = val9;
		return this;
	}

	public final int val9() {
		return this.val9;
	}

	public final ClientboundMyDownloadMapPacket val10(byte val10) {
		this.val10 = val10;
		return this;
	}

	public final byte val10() {
		return this.val10;
	}

	public final ClientboundMyDownloadMapPacket val11(byte val11) {
		this.val11 = val11;
		return this;
	}

	public final byte val11() {
		return this.val11;
	}

	public final ClientboundMyDownloadMapPacket val12(byte val12) {
		this.val12 = val12;
		return this;
	}

	public final byte val12() {
		return this.val12;
	}

	public final ClientboundMyDownloadMapPacket val13(byte val13) {
		this.val13 = val13;
		return this;
	}

	public final byte val13() {
		return this.val13;
	}

	public final ClientboundMyDownloadMapPacket val14(byte val14) {
		this.val14 = val14;
		return this;
	}

	public final byte val14() {
		return this.val14;
	}

	public final ClientboundMyDownloadMapPacket val15(int val15) {
		this.val15 = val15;
		return this;
	}

	public final int val15() {
		return this.val15;
	}

	public final ClientboundMyDownloadMapPacket val16(int val16) {
		this.val16 = val16;
		return this;
	}

	public final int val16() {
		return this.val16;
	}

	public final ClientboundMyDownloadMapPacket val17(int val17) {
		this.val17 = val17;
		return this;
	}

	public final int val17() {
		return this.val17;
	}

	public final ClientboundMyDownloadMapPacket val18(int val18) {
		this.val18 = val18;
		return this;
	}

	public final int val18() {
		return this.val18;
	}

	public final ClientboundMyDownloadMapPacket val19(int val19) {
		this.val19 = val19;
		return this;
	}

	public final int val19() {
		return this.val19;
	}

	public final ClientboundMyDownloadMapPacket val20(int val20) {
		this.val20 = val20;
		return this;
	}

	public final int val20() {
		return this.val20;
	}

	@Override
	public int packetId() {
		return 426;
	}

	@Override
	public final void write(PacketBuf buf) {
		buf.writeInt(this.val);
		buf.writeInt(this.val2);
		buf.writeInt(this.val3);
		buf.writeString(this.val4);
		buf.writeString(this.val5);
		buf.writeShort(this.val6);
		buf.writeByte(this.val7);
		buf.writeByte(this.val8);
		buf.writeInt(this.val9);
		buf.writeByte(this.val10);
		buf.writeByte(this.val11);
		buf.writeByte(this.val12);
		buf.writeByte(this.val13);
		buf.writeByte(this.val14);
		buf.writeInt(this.val15);
		buf.writeInt(this.val16);
		buf.writeInt(this.val17);
		buf.writeInt(this.val18);
		buf.writeInt(this.val19);
		buf.writeInt(this.val20);
	}
}