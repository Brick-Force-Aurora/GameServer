package de.brickforceaurora.server.net.protocol.clientbound.original;

import de.brickforceaurora.server.net.protocol.IClientboundPacket;
import de.brickforceaurora.server.net.protocol.PacketBuf;

public final class ClientboundBndModeEndPacket implements IClientboundPacket {

	private byte val;
	private int val2;
	private int val3;
	private int val4;
	private int val5;
	private int val6;
	private boolean val7;
	private int val8;
	private String val9;
	private int val10;
	private int val11;
	private int val12;
	private int val13;
	private int val14;
	private int val15;
	private int val16;
	private int val17;
	private int val18;
	private long val19;

	public final ClientboundBndModeEndPacket val(byte val) {
		this.val = val;
		return this;
	}

	public final byte val() {
		return this.val;
	}

	public final ClientboundBndModeEndPacket val2(int val2) {
		this.val2 = val2;
		return this;
	}

	public final int val2() {
		return this.val2;
	}

	public final ClientboundBndModeEndPacket val3(int val3) {
		this.val3 = val3;
		return this;
	}

	public final int val3() {
		return this.val3;
	}

	public final ClientboundBndModeEndPacket val4(int val4) {
		this.val4 = val4;
		return this;
	}

	public final int val4() {
		return this.val4;
	}

	public final ClientboundBndModeEndPacket val5(int val5) {
		this.val5 = val5;
		return this;
	}

	public final int val5() {
		return this.val5;
	}

	public final ClientboundBndModeEndPacket val6(int val6) {
		this.val6 = val6;
		return this;
	}

	public final int val6() {
		return this.val6;
	}

	public final ClientboundBndModeEndPacket val7(boolean val7) {
		this.val7 = val7;
		return this;
	}

	public final boolean val7() {
		return this.val7;
	}

	public final ClientboundBndModeEndPacket val8(int val8) {
		this.val8 = val8;
		return this;
	}

	public final int val8() {
		return this.val8;
	}

	public final ClientboundBndModeEndPacket val9(String val9) {
		this.val9 = val9;
		return this;
	}

	public final String val9() {
		return this.val9;
	}

	public final ClientboundBndModeEndPacket val10(int val10) {
		this.val10 = val10;
		return this;
	}

	public final int val10() {
		return this.val10;
	}

	public final ClientboundBndModeEndPacket val11(int val11) {
		this.val11 = val11;
		return this;
	}

	public final int val11() {
		return this.val11;
	}

	public final ClientboundBndModeEndPacket val12(int val12) {
		this.val12 = val12;
		return this;
	}

	public final int val12() {
		return this.val12;
	}

	public final ClientboundBndModeEndPacket val13(int val13) {
		this.val13 = val13;
		return this;
	}

	public final int val13() {
		return this.val13;
	}

	public final ClientboundBndModeEndPacket val14(int val14) {
		this.val14 = val14;
		return this;
	}

	public final int val14() {
		return this.val14;
	}

	public final ClientboundBndModeEndPacket val15(int val15) {
		this.val15 = val15;
		return this;
	}

	public final int val15() {
		return this.val15;
	}

	public final ClientboundBndModeEndPacket val16(int val16) {
		this.val16 = val16;
		return this;
	}

	public final int val16() {
		return this.val16;
	}

	public final ClientboundBndModeEndPacket val17(int val17) {
		this.val17 = val17;
		return this;
	}

	public final int val17() {
		return this.val17;
	}

	public final ClientboundBndModeEndPacket val18(int val18) {
		this.val18 = val18;
		return this;
	}

	public final int val18() {
		return this.val18;
	}

	public final ClientboundBndModeEndPacket val19(long val19) {
		this.val19 = val19;
		return this;
	}

	public final long val19() {
		return this.val19;
	}

	@Override
	public int packetId() {
		return 338;
	}

	@Override
	public final void write(PacketBuf buf) {
		buf.writeByte(this.val);
		buf.writeInt(this.val2);
		buf.writeInt(this.val3);
		buf.writeInt(this.val4);
		buf.writeInt(this.val5);
		buf.writeInt(this.val6);
		buf.writeBoolean(this.val7);
		buf.writeInt(this.val8);
		buf.writeString(this.val9);
		buf.writeInt(this.val10);
		buf.writeInt(this.val11);
		buf.writeInt(this.val12);
		buf.writeInt(this.val13);
		buf.writeInt(this.val14);
		buf.writeInt(this.val15);
		buf.writeInt(this.val16);
		buf.writeInt(this.val17);
		buf.writeInt(this.val18);
		buf.writeLong(this.val19);
	}
}