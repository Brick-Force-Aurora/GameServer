package de.brickforceaurora.server.net.protocol.clientbound.original;

import de.brickforceaurora.server.net.protocol.IClientboundPacket;
import de.brickforceaurora.server.net.protocol.PacketBuf;

public final class ClientboundWeeklyChartPacket implements IClientboundPacket {

	private int val;
	private int val2;
	private int val3;
	private byte val4;
	private byte val5;
	private int val6;
	private int val7;
	private int val8;
	private int val9;
	private String val10;
	private String val11;
	private int val12;
	private int val13;
	private int val14;
	private int val15;
	private byte val16;
	private byte val17;
	private byte val18;
	private byte val19;
	private byte val20;
	private int val21;
	private int val22;
	private int val23;
	private int val24;
	private int val25;
	private int val26;

	public final ClientboundWeeklyChartPacket val(int val) {
		this.val = val;
		return this;
	}

	public final int val() {
		return this.val;
	}

	public final ClientboundWeeklyChartPacket val2(int val2) {
		this.val2 = val2;
		return this;
	}

	public final int val2() {
		return this.val2;
	}

	public final ClientboundWeeklyChartPacket val3(int val3) {
		this.val3 = val3;
		return this;
	}

	public final int val3() {
		return this.val3;
	}

	public final ClientboundWeeklyChartPacket val4(byte val4) {
		this.val4 = val4;
		return this;
	}

	public final byte val4() {
		return this.val4;
	}

	public final ClientboundWeeklyChartPacket val5(byte val5) {
		this.val5 = val5;
		return this;
	}

	public final byte val5() {
		return this.val5;
	}

	public final ClientboundWeeklyChartPacket val6(int val6) {
		this.val6 = val6;
		return this;
	}

	public final int val6() {
		return this.val6;
	}

	public final ClientboundWeeklyChartPacket val7(int val7) {
		this.val7 = val7;
		return this;
	}

	public final int val7() {
		return this.val7;
	}

	public final ClientboundWeeklyChartPacket val8(int val8) {
		this.val8 = val8;
		return this;
	}

	public final int val8() {
		return this.val8;
	}

	public final ClientboundWeeklyChartPacket val9(int val9) {
		this.val9 = val9;
		return this;
	}

	public final int val9() {
		return this.val9;
	}

	public final ClientboundWeeklyChartPacket val10(String val10) {
		this.val10 = val10;
		return this;
	}

	public final String val10() {
		return this.val10;
	}

	public final ClientboundWeeklyChartPacket val11(String val11) {
		this.val11 = val11;
		return this;
	}

	public final String val11() {
		return this.val11;
	}

	public final ClientboundWeeklyChartPacket val12(int val12) {
		if (val12 > 65535L || val12 < 0L) {
			throw new IllegalArgumentException(
					"Value " + val12 + " is out of bounds of allowed number range of 0 - 65535");
		}
		this.val12 = val12;
		return this;
	}

	public final int val12() {
		return this.val12;
	}

	public final ClientboundWeeklyChartPacket val13(int val13) {
		if (val13 > 255L || val13 < 0L) {
			throw new IllegalArgumentException(
					"Value " + val13 + " is out of bounds of allowed number range of 0 - 255");
		}
		this.val13 = val13;
		return this;
	}

	public final int val13() {
		return this.val13;
	}

	public final ClientboundWeeklyChartPacket val14(int val14) {
		if (val14 > 255L || val14 < 0L) {
			throw new IllegalArgumentException(
					"Value " + val14 + " is out of bounds of allowed number range of 0 - 255");
		}
		this.val14 = val14;
		return this;
	}

	public final int val14() {
		return this.val14;
	}

	public final ClientboundWeeklyChartPacket val15(int val15) {
		this.val15 = val15;
		return this;
	}

	public final int val15() {
		return this.val15;
	}

	public final ClientboundWeeklyChartPacket val16(byte val16) {
		this.val16 = val16;
		return this;
	}

	public final byte val16() {
		return this.val16;
	}

	public final ClientboundWeeklyChartPacket val17(byte val17) {
		this.val17 = val17;
		return this;
	}

	public final byte val17() {
		return this.val17;
	}

	public final ClientboundWeeklyChartPacket val18(byte val18) {
		this.val18 = val18;
		return this;
	}

	public final byte val18() {
		return this.val18;
	}

	public final ClientboundWeeklyChartPacket val19(byte val19) {
		this.val19 = val19;
		return this;
	}

	public final byte val19() {
		return this.val19;
	}

	public final ClientboundWeeklyChartPacket val20(byte val20) {
		this.val20 = val20;
		return this;
	}

	public final byte val20() {
		return this.val20;
	}

	public final ClientboundWeeklyChartPacket val21(int val21) {
		this.val21 = val21;
		return this;
	}

	public final int val21() {
		return this.val21;
	}

	public final ClientboundWeeklyChartPacket val22(int val22) {
		this.val22 = val22;
		return this;
	}

	public final int val22() {
		return this.val22;
	}

	public final ClientboundWeeklyChartPacket val23(int val23) {
		this.val23 = val23;
		return this;
	}

	public final int val23() {
		return this.val23;
	}

	public final ClientboundWeeklyChartPacket val24(int val24) {
		this.val24 = val24;
		return this;
	}

	public final int val24() {
		return this.val24;
	}

	public final ClientboundWeeklyChartPacket val25(int val25) {
		this.val25 = val25;
		return this;
	}

	public final int val25() {
		return this.val25;
	}

	public final ClientboundWeeklyChartPacket val26(int val26) {
		this.val26 = val26;
		return this;
	}

	public final int val26() {
		return this.val26;
	}

	@Override
	public int packetId() {
		return 434;
	}

	@Override
	public final void write(PacketBuf buf) {
		buf.writeInt(this.val);
		buf.writeInt(this.val2);
		buf.writeInt(this.val3);
		buf.writeByte(this.val4);
		buf.writeByte(this.val5);
		buf.writeInt(this.val6);
		buf.writeInt(this.val7);
		buf.writeInt(this.val8);
		buf.writeInt(this.val9);
		buf.writeString(this.val10);
		buf.writeString(this.val11);
		buf.writeShort(this.val12);
		buf.writeByte(this.val13);
		buf.writeByte(this.val14);
		buf.writeInt(this.val15);
		buf.writeByte(this.val16);
		buf.writeByte(this.val17);
		buf.writeByte(this.val18);
		buf.writeByte(this.val19);
		buf.writeByte(this.val20);
		buf.writeInt(this.val21);
		buf.writeInt(this.val22);
		buf.writeInt(this.val23);
		buf.writeInt(this.val24);
		buf.writeInt(this.val25);
		buf.writeInt(this.val26);
	}
}