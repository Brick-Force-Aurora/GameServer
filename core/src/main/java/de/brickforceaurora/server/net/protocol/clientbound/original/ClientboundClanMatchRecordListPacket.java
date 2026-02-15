package de.brickforceaurora.server.net.protocol.clientbound.original;

import de.brickforceaurora.server.net.protocol.IClientboundPacket;
import de.brickforceaurora.server.net.protocol.PacketBuf;

public final class ClientboundClanMatchRecordListPacket implements IClientboundPacket {

	private int val;
	private int val2;
	private long val3;
	private int val4;
	private int val5;
	private int val6;
	private int val7;
	private String val8;
	private int val9;
	private int val10;
	private int val11;
	private int val12;
	private int val13;
	private int val14;
	private int val15;
	private int val16;

	public final ClientboundClanMatchRecordListPacket val(int val) {
		this.val = val;
		return this;
	}

	public final int val() {
		return this.val;
	}

	public final ClientboundClanMatchRecordListPacket val2(int val2) {
		this.val2 = val2;
		return this;
	}

	public final int val2() {
		return this.val2;
	}

	public final ClientboundClanMatchRecordListPacket val3(long val3) {
		this.val3 = val3;
		return this;
	}

	public final long val3() {
		return this.val3;
	}

	public final ClientboundClanMatchRecordListPacket val4(int val4) {
		this.val4 = val4;
		return this;
	}

	public final int val4() {
		return this.val4;
	}

	public final ClientboundClanMatchRecordListPacket val5(int val5) {
		this.val5 = val5;
		return this;
	}

	public final int val5() {
		return this.val5;
	}

	public final ClientboundClanMatchRecordListPacket val6(int val6) {
		this.val6 = val6;
		return this;
	}

	public final int val6() {
		return this.val6;
	}

	public final ClientboundClanMatchRecordListPacket val7(int val7) {
		this.val7 = val7;
		return this;
	}

	public final int val7() {
		return this.val7;
	}

	public final ClientboundClanMatchRecordListPacket val8(String val8) {
		this.val8 = val8;
		return this;
	}

	public final String val8() {
		return this.val8;
	}

	public final ClientboundClanMatchRecordListPacket val9(int val9) {
		this.val9 = val9;
		return this;
	}

	public final int val9() {
		return this.val9;
	}

	public final ClientboundClanMatchRecordListPacket val10(int val10) {
		this.val10 = val10;
		return this;
	}

	public final int val10() {
		return this.val10;
	}

	public final ClientboundClanMatchRecordListPacket val11(int val11) {
		this.val11 = val11;
		return this;
	}

	public final int val11() {
		return this.val11;
	}

	public final ClientboundClanMatchRecordListPacket val12(int val12) {
		this.val12 = val12;
		return this;
	}

	public final int val12() {
		return this.val12;
	}

	public final ClientboundClanMatchRecordListPacket val13(int val13) {
		this.val13 = val13;
		return this;
	}

	public final int val13() {
		return this.val13;
	}

	public final ClientboundClanMatchRecordListPacket val14(int val14) {
		this.val14 = val14;
		return this;
	}

	public final int val14() {
		return this.val14;
	}

	public final ClientboundClanMatchRecordListPacket val15(int val15) {
		this.val15 = val15;
		return this;
	}

	public final int val15() {
		return this.val15;
	}

	public final ClientboundClanMatchRecordListPacket val16(int val16) {
		this.val16 = val16;
		return this;
	}

	public final int val16() {
		return this.val16;
	}

	@Override
	public int packetId() {
		return 269;
	}

	@Override
	public final void write(PacketBuf buf) {
		buf.writeInt(this.val);
		buf.writeInt(this.val2);
		buf.writeLong(this.val3);
		buf.writeInt(this.val4);
		buf.writeInt(this.val5);
		buf.writeInt(this.val6);
		buf.writeInt(this.val7);
		buf.writeString(this.val8);
		buf.writeInt(this.val9);
		buf.writeInt(this.val10);
		buf.writeInt(this.val11);
		buf.writeInt(this.val12);
		buf.writeInt(this.val13);
		buf.writeInt(this.val14);
		buf.writeInt(this.val15);
		buf.writeInt(this.val16);
	}
}