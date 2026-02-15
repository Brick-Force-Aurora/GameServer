package de.brickforceaurora.server.net.protocol.clientbound.original;

import de.brickforceaurora.server.net.protocol.IClientboundPacket;
import de.brickforceaurora.server.net.protocol.PacketBuf;

public final class ClientboundMapHonorPacket implements IClientboundPacket {

	private int val;
	private int val2;
	private int val3;
	private int val4;
	private String val5;
	private String val6;
	private int val7;
	private int val8;
	private int val9;
	private int val10;
	private byte val11;
	private byte val12;
	private byte val13;
	private byte val14;
	private byte val15;
	private int val16;
	private int val17;
	private int val18;
	private int val19;
	private int val20;
	private int val21;

	public final ClientboundMapHonorPacket val(int val) {
		this.val = val;
		return this;
	}

	public final int val() {
		return this.val;
	}

	public final ClientboundMapHonorPacket val2(int val2) {
		this.val2 = val2;
		return this;
	}

	public final int val2() {
		return this.val2;
	}

	public final ClientboundMapHonorPacket val3(int val3) {
		this.val3 = val3;
		return this;
	}

	public final int val3() {
		return this.val3;
	}

	public final ClientboundMapHonorPacket val4(int val4) {
		this.val4 = val4;
		return this;
	}

	public final int val4() {
		return this.val4;
	}

	public final ClientboundMapHonorPacket val5(String val5) {
		this.val5 = val5;
		return this;
	}

	public final String val5() {
		return this.val5;
	}

	public final ClientboundMapHonorPacket val6(String val6) {
		this.val6 = val6;
		return this;
	}

	public final String val6() {
		return this.val6;
	}

	public final ClientboundMapHonorPacket val7(int val7) {
		if (val7 > 65535L || val7 < 0L) {
			throw new IllegalArgumentException(
					"Value " + val7 + " is out of bounds of allowed number range of 0 - 65535");
		}
		this.val7 = val7;
		return this;
	}

	public final int val7() {
		return this.val7;
	}

	public final ClientboundMapHonorPacket val8(int val8) {
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

	public final ClientboundMapHonorPacket val9(int val9) {
		if (val9 > 255L || val9 < 0L) {
			throw new IllegalArgumentException(
					"Value " + val9 + " is out of bounds of allowed number range of 0 - 255");
		}
		this.val9 = val9;
		return this;
	}

	public final int val9() {
		return this.val9;
	}

	public final ClientboundMapHonorPacket val10(int val10) {
		this.val10 = val10;
		return this;
	}

	public final int val10() {
		return this.val10;
	}

	public final ClientboundMapHonorPacket val11(byte val11) {
		this.val11 = val11;
		return this;
	}

	public final byte val11() {
		return this.val11;
	}

	public final ClientboundMapHonorPacket val12(byte val12) {
		this.val12 = val12;
		return this;
	}

	public final byte val12() {
		return this.val12;
	}

	public final ClientboundMapHonorPacket val13(byte val13) {
		this.val13 = val13;
		return this;
	}

	public final byte val13() {
		return this.val13;
	}

	public final ClientboundMapHonorPacket val14(byte val14) {
		this.val14 = val14;
		return this;
	}

	public final byte val14() {
		return this.val14;
	}

	public final ClientboundMapHonorPacket val15(byte val15) {
		this.val15 = val15;
		return this;
	}

	public final byte val15() {
		return this.val15;
	}

	public final ClientboundMapHonorPacket val16(int val16) {
		this.val16 = val16;
		return this;
	}

	public final int val16() {
		return this.val16;
	}

	public final ClientboundMapHonorPacket val17(int val17) {
		this.val17 = val17;
		return this;
	}

	public final int val17() {
		return this.val17;
	}

	public final ClientboundMapHonorPacket val18(int val18) {
		this.val18 = val18;
		return this;
	}

	public final int val18() {
		return this.val18;
	}

	public final ClientboundMapHonorPacket val19(int val19) {
		this.val19 = val19;
		return this;
	}

	public final int val19() {
		return this.val19;
	}

	public final ClientboundMapHonorPacket val20(int val20) {
		this.val20 = val20;
		return this;
	}

	public final int val20() {
		return this.val20;
	}

	public final ClientboundMapHonorPacket val21(int val21) {
		this.val21 = val21;
		return this;
	}

	public final int val21() {
		return this.val21;
	}

	@Override
	public int packetId() {
		return 436;
	}

	@Override
	public final void write(PacketBuf buf) {
		buf.writeInt(this.val);
		buf.writeInt(this.val2);
		buf.writeInt(this.val3);
		buf.writeInt(this.val4);
		buf.writeString(this.val5);
		buf.writeString(this.val6);
		buf.writeShort(this.val7);
		buf.writeByte(this.val8);
		buf.writeByte(this.val9);
		buf.writeInt(this.val10);
		buf.writeByte(this.val11);
		buf.writeByte(this.val12);
		buf.writeByte(this.val13);
		buf.writeByte(this.val14);
		buf.writeByte(this.val15);
		buf.writeInt(this.val16);
		buf.writeInt(this.val17);
		buf.writeInt(this.val18);
		buf.writeInt(this.val19);
		buf.writeInt(this.val20);
		buf.writeInt(this.val21);
	}
}