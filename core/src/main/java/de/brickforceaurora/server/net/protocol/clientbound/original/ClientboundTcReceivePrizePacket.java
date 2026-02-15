package de.brickforceaurora.server.net.protocol.clientbound.original;

import de.brickforceaurora.server.net.protocol.IClientboundPacket;
import de.brickforceaurora.server.net.protocol.PacketBuf;

public final class ClientboundTcReceivePrizePacket implements IClientboundPacket {

	private int val;
	private long val2;
	private String val3;
	private byte val4;
	private int val5;
	private int val6;
	private int val7;
	private boolean val8;
	private int val9;

	public final ClientboundTcReceivePrizePacket val(int val) {
		this.val = val;
		return this;
	}

	public final int val() {
		return this.val;
	}

	public final ClientboundTcReceivePrizePacket val2(long val2) {
		this.val2 = val2;
		return this;
	}

	public final long val2() {
		return this.val2;
	}

	public final ClientboundTcReceivePrizePacket val3(String val3) {
		this.val3 = val3;
		return this;
	}

	public final String val3() {
		return this.val3;
	}

	public final ClientboundTcReceivePrizePacket val4(byte val4) {
		this.val4 = val4;
		return this;
	}

	public final byte val4() {
		return this.val4;
	}

	public final ClientboundTcReceivePrizePacket val5(int val5) {
		this.val5 = val5;
		return this;
	}

	public final int val5() {
		return this.val5;
	}

	public final ClientboundTcReceivePrizePacket val6(int val6) {
		this.val6 = val6;
		return this;
	}

	public final int val6() {
		return this.val6;
	}

	public final ClientboundTcReceivePrizePacket val7(int val7) {
		this.val7 = val7;
		return this;
	}

	public final int val7() {
		return this.val7;
	}

	public final ClientboundTcReceivePrizePacket val8(boolean val8) {
		this.val8 = val8;
		return this;
	}

	public final boolean val8() {
		return this.val8;
	}

	public final ClientboundTcReceivePrizePacket val9(int val9) {
		this.val9 = val9;
		return this;
	}

	public final int val9() {
		return this.val9;
	}

	@Override
	public int packetId() {
		return 380;
	}

	@Override
	public final void write(PacketBuf buf) {
		buf.writeInt(this.val);
		buf.writeLong(this.val2);
		buf.writeString(this.val3);
		buf.writeByte(this.val4);
		buf.writeInt(this.val5);
		buf.writeInt(this.val6);
		buf.writeInt(this.val7);
		buf.writeBoolean(this.val8);
		buf.writeInt(this.val9);
	}
}