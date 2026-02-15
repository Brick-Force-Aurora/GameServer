package de.brickforceaurora.server.net.protocol.clientbound.original;

import de.brickforceaurora.server.net.protocol.IClientboundPacket;
import de.brickforceaurora.server.net.protocol.PacketBuf;

public final class ClientboundEscapeModeEndPacket implements IClientboundPacket {

	private int val;
	private boolean val2;
	private int val3;
	private String val4;
	private int val5;
	private int val6;
	private int val7;
	private int val8;
	private int val9;
	private int val10;
	private int val11;
	private int val12;
	private int val13;
	private long val14;
	private int val15;

	public final ClientboundEscapeModeEndPacket val(int val) {
		this.val = val;
		return this;
	}

	public final int val() {
		return this.val;
	}

	public final ClientboundEscapeModeEndPacket val2(boolean val2) {
		this.val2 = val2;
		return this;
	}

	public final boolean val2() {
		return this.val2;
	}

	public final ClientboundEscapeModeEndPacket val3(int val3) {
		this.val3 = val3;
		return this;
	}

	public final int val3() {
		return this.val3;
	}

	public final ClientboundEscapeModeEndPacket val4(String val4) {
		this.val4 = val4;
		return this;
	}

	public final String val4() {
		return this.val4;
	}

	public final ClientboundEscapeModeEndPacket val5(int val5) {
		this.val5 = val5;
		return this;
	}

	public final int val5() {
		return this.val5;
	}

	public final ClientboundEscapeModeEndPacket val6(int val6) {
		this.val6 = val6;
		return this;
	}

	public final int val6() {
		return this.val6;
	}

	public final ClientboundEscapeModeEndPacket val7(int val7) {
		this.val7 = val7;
		return this;
	}

	public final int val7() {
		return this.val7;
	}

	public final ClientboundEscapeModeEndPacket val8(int val8) {
		this.val8 = val8;
		return this;
	}

	public final int val8() {
		return this.val8;
	}

	public final ClientboundEscapeModeEndPacket val9(int val9) {
		this.val9 = val9;
		return this;
	}

	public final int val9() {
		return this.val9;
	}

	public final ClientboundEscapeModeEndPacket val10(int val10) {
		this.val10 = val10;
		return this;
	}

	public final int val10() {
		return this.val10;
	}

	public final ClientboundEscapeModeEndPacket val11(int val11) {
		this.val11 = val11;
		return this;
	}

	public final int val11() {
		return this.val11;
	}

	public final ClientboundEscapeModeEndPacket val12(int val12) {
		this.val12 = val12;
		return this;
	}

	public final int val12() {
		return this.val12;
	}

	public final ClientboundEscapeModeEndPacket val13(int val13) {
		this.val13 = val13;
		return this;
	}

	public final int val13() {
		return this.val13;
	}

	public final ClientboundEscapeModeEndPacket val14(long val14) {
		this.val14 = val14;
		return this;
	}

	public final long val14() {
		return this.val14;
	}

	public final ClientboundEscapeModeEndPacket val15(int val15) {
		this.val15 = val15;
		return this;
	}

	public final int val15() {
		return this.val15;
	}

	@Override
	public int packetId() {
		return 521;
	}

	@Override
	public final void write(PacketBuf buf) {
		buf.writeInt(this.val);
		buf.writeBoolean(this.val2);
		buf.writeInt(this.val3);
		buf.writeString(this.val4);
		buf.writeInt(this.val5);
		buf.writeInt(this.val6);
		buf.writeInt(this.val7);
		buf.writeInt(this.val8);
		buf.writeInt(this.val9);
		buf.writeInt(this.val10);
		buf.writeInt(this.val11);
		buf.writeInt(this.val12);
		buf.writeInt(this.val13);
		buf.writeLong(this.val14);
		buf.writeInt(this.val15);
	}
}