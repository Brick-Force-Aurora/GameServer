package de.brickforceaurora.server.net.protocol.clientbound.original;

import de.brickforceaurora.server.net.protocol.IClientboundPacket;
import de.brickforceaurora.server.net.protocol.PacketBuf;

public final class ClientboundDownloadedMapListPacket implements IClientboundPacket {

	private int val;
	private int val2;
	private String val3;
	private String val4;
	private int val5;
	private int val6;
	private int val7;
	private int val8;
	private byte val9;
	private byte val10;
	private byte val11;
	private byte val12;
	private byte val13;
	private int val14;
	private int val15;
	private int val16;
	private int val17;
	private int val18;
	private int val19;

	public final ClientboundDownloadedMapListPacket val(int val) {
		this.val = val;
		return this;
	}

	public final int val() {
		return this.val;
	}

	public final ClientboundDownloadedMapListPacket val2(int val2) {
		this.val2 = val2;
		return this;
	}

	public final int val2() {
		return this.val2;
	}

	public final ClientboundDownloadedMapListPacket val3(String val3) {
		this.val3 = val3;
		return this;
	}

	public final String val3() {
		return this.val3;
	}

	public final ClientboundDownloadedMapListPacket val4(String val4) {
		this.val4 = val4;
		return this;
	}

	public final String val4() {
		return this.val4;
	}

	public final ClientboundDownloadedMapListPacket val5(int val5) {
		if (val5 > 65535L || val5 < 0L) {
			throw new IllegalArgumentException(
					"Value " + val5 + " is out of bounds of allowed number range of 0 - 65535");
		}
		this.val5 = val5;
		return this;
	}

	public final int val5() {
		return this.val5;
	}

	public final ClientboundDownloadedMapListPacket val6(int val6) {
		if (val6 > 255L || val6 < 0L) {
			throw new IllegalArgumentException(
					"Value " + val6 + " is out of bounds of allowed number range of 0 - 255");
		}
		this.val6 = val6;
		return this;
	}

	public final int val6() {
		return this.val6;
	}

	public final ClientboundDownloadedMapListPacket val7(int val7) {
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

	public final ClientboundDownloadedMapListPacket val8(int val8) {
		this.val8 = val8;
		return this;
	}

	public final int val8() {
		return this.val8;
	}

	public final ClientboundDownloadedMapListPacket val9(byte val9) {
		this.val9 = val9;
		return this;
	}

	public final byte val9() {
		return this.val9;
	}

	public final ClientboundDownloadedMapListPacket val10(byte val10) {
		this.val10 = val10;
		return this;
	}

	public final byte val10() {
		return this.val10;
	}

	public final ClientboundDownloadedMapListPacket val11(byte val11) {
		this.val11 = val11;
		return this;
	}

	public final byte val11() {
		return this.val11;
	}

	public final ClientboundDownloadedMapListPacket val12(byte val12) {
		this.val12 = val12;
		return this;
	}

	public final byte val12() {
		return this.val12;
	}

	public final ClientboundDownloadedMapListPacket val13(byte val13) {
		this.val13 = val13;
		return this;
	}

	public final byte val13() {
		return this.val13;
	}

	public final ClientboundDownloadedMapListPacket val14(int val14) {
		this.val14 = val14;
		return this;
	}

	public final int val14() {
		return this.val14;
	}

	public final ClientboundDownloadedMapListPacket val15(int val15) {
		this.val15 = val15;
		return this;
	}

	public final int val15() {
		return this.val15;
	}

	public final ClientboundDownloadedMapListPacket val16(int val16) {
		this.val16 = val16;
		return this;
	}

	public final int val16() {
		return this.val16;
	}

	public final ClientboundDownloadedMapListPacket val17(int val17) {
		this.val17 = val17;
		return this;
	}

	public final int val17() {
		return this.val17;
	}

	public final ClientboundDownloadedMapListPacket val18(int val18) {
		this.val18 = val18;
		return this;
	}

	public final int val18() {
		return this.val18;
	}

	public final ClientboundDownloadedMapListPacket val19(int val19) {
		this.val19 = val19;
		return this;
	}

	public final int val19() {
		return this.val19;
	}

	@Override
	public int packetId() {
		return 465;
	}

	@Override
	public final void write(PacketBuf buf) {
		buf.writeInt(this.val);
		buf.writeInt(this.val2);
		buf.writeString(this.val3);
		buf.writeString(this.val4);
		buf.writeShort(this.val5);
		buf.writeByte(this.val6);
		buf.writeByte(this.val7);
		buf.writeInt(this.val8);
		buf.writeByte(this.val9);
		buf.writeByte(this.val10);
		buf.writeByte(this.val11);
		buf.writeByte(this.val12);
		buf.writeByte(this.val13);
		buf.writeInt(this.val14);
		buf.writeInt(this.val15);
		buf.writeInt(this.val16);
		buf.writeInt(this.val17);
		buf.writeInt(this.val18);
		buf.writeInt(this.val19);
	}
}