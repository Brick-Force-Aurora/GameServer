package de.brickforceaurora.server.net.protocol.clientbound.original;

import de.brickforceaurora.server.net.protocol.IClientboundPacket;
import de.brickforceaurora.server.net.protocol.PacketBuf;

public final class ClientboundShopPacket implements IClientboundPacket {

	private int val;
	private String val2;
	private byte val3;
	private byte val4;
	private byte val5;
	private int val6;
	private byte val7;
	private byte val8;
	private byte val9;
	private byte val10;
	private int val11;
	private int val12;
	private int val13;
	private int val14;
	private int val15;
	private int val16;
	private int val17;
	private int val18;
	private int val19;
	private int val20;
	private int val21;
	private int val22;
	private byte val23;
	private byte val24;
	private byte val25;

	public final ClientboundShopPacket val(int val) {
		this.val = val;
		return this;
	}

	public final int val() {
		return this.val;
	}

	public final ClientboundShopPacket val2(String val2) {
		this.val2 = val2;
		return this;
	}

	public final String val2() {
		return this.val2;
	}

	public final ClientboundShopPacket val3(byte val3) {
		this.val3 = val3;
		return this;
	}

	public final byte val3() {
		return this.val3;
	}

	public final ClientboundShopPacket val4(byte val4) {
		this.val4 = val4;
		return this;
	}

	public final byte val4() {
		return this.val4;
	}

	public final ClientboundShopPacket val5(byte val5) {
		this.val5 = val5;
		return this;
	}

	public final byte val5() {
		return this.val5;
	}

	public final ClientboundShopPacket val6(int val6) {
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

	public final ClientboundShopPacket val7(byte val7) {
		this.val7 = val7;
		return this;
	}

	public final byte val7() {
		return this.val7;
	}

	public final ClientboundShopPacket val8(byte val8) {
		this.val8 = val8;
		return this;
	}

	public final byte val8() {
		return this.val8;
	}

	public final ClientboundShopPacket val9(byte val9) {
		this.val9 = val9;
		return this;
	}

	public final byte val9() {
		return this.val9;
	}

	public final ClientboundShopPacket val10(byte val10) {
		this.val10 = val10;
		return this;
	}

	public final byte val10() {
		return this.val10;
	}

	public final ClientboundShopPacket val11(int val11) {
		this.val11 = val11;
		return this;
	}

	public final int val11() {
		return this.val11;
	}

	public final ClientboundShopPacket val12(int val12) {
		this.val12 = val12;
		return this;
	}

	public final int val12() {
		return this.val12;
	}

	public final ClientboundShopPacket val13(int val13) {
		this.val13 = val13;
		return this;
	}

	public final int val13() {
		return this.val13;
	}

	public final ClientboundShopPacket val14(int val14) {
		this.val14 = val14;
		return this;
	}

	public final int val14() {
		return this.val14;
	}

	public final ClientboundShopPacket val15(int val15) {
		this.val15 = val15;
		return this;
	}

	public final int val15() {
		return this.val15;
	}

	public final ClientboundShopPacket val16(int val16) {
		this.val16 = val16;
		return this;
	}

	public final int val16() {
		return this.val16;
	}

	public final ClientboundShopPacket val17(int val17) {
		this.val17 = val17;
		return this;
	}

	public final int val17() {
		return this.val17;
	}

	public final ClientboundShopPacket val18(int val18) {
		this.val18 = val18;
		return this;
	}

	public final int val18() {
		return this.val18;
	}

	public final ClientboundShopPacket val19(int val19) {
		this.val19 = val19;
		return this;
	}

	public final int val19() {
		return this.val19;
	}

	public final ClientboundShopPacket val20(int val20) {
		this.val20 = val20;
		return this;
	}

	public final int val20() {
		return this.val20;
	}

	public final ClientboundShopPacket val21(int val21) {
		this.val21 = val21;
		return this;
	}

	public final int val21() {
		return this.val21;
	}

	public final ClientboundShopPacket val22(int val22) {
		this.val22 = val22;
		return this;
	}

	public final int val22() {
		return this.val22;
	}

	public final ClientboundShopPacket val23(byte val23) {
		this.val23 = val23;
		return this;
	}

	public final byte val23() {
		return this.val23;
	}

	public final ClientboundShopPacket val24(byte val24) {
		this.val24 = val24;
		return this;
	}

	public final byte val24() {
		return this.val24;
	}

	public final ClientboundShopPacket val25(byte val25) {
		this.val25 = val25;
		return this;
	}

	public final byte val25() {
		return this.val25;
	}

	@Override
	public int packetId() {
		return 318;
	}

	@Override
	public final void write(PacketBuf buf) {
		buf.writeInt(this.val);
		buf.writeString(this.val2);
		buf.writeByte(this.val3);
		buf.writeByte(this.val4);
		buf.writeByte(this.val5);
		buf.writeByte(this.val6);
		buf.writeByte(this.val7);
		buf.writeByte(this.val8);
		buf.writeByte(this.val9);
		buf.writeByte(this.val10);
		buf.writeInt(this.val11);
		buf.writeInt(this.val12);
		buf.writeInt(this.val13);
		buf.writeInt(this.val14);
		buf.writeInt(this.val15);
		buf.writeInt(this.val16);
		buf.writeInt(this.val17);
		buf.writeInt(this.val18);
		buf.writeInt(this.val19);
		buf.writeInt(this.val20);
		buf.writeInt(this.val21);
		buf.writeInt(this.val22);
		buf.writeByte(this.val23);
		buf.writeByte(this.val24);
		buf.writeByte(this.val25);
	}
}