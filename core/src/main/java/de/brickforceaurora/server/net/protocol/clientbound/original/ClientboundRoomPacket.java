package de.brickforceaurora.server.net.protocol.clientbound.original;

import de.brickforceaurora.server.net.protocol.IClientboundPacket;
import de.brickforceaurora.server.net.protocol.PacketBuf;

public final class ClientboundRoomPacket implements IClientboundPacket {

	private int val;
	private int val2;
	private String val3;
	private boolean val4;
	private int val5;
	private int val6;
	private int val7;
	private int val8;
	private String val9;
	private int val10;
	private int val11;
	private int val12;
	private int val13;
	private int val14;
	private int val15;
	private int val16;
	private boolean val17;
	private boolean val18;
	private boolean val19;
	private int val20;
	private int val21;

	public final ClientboundRoomPacket val(int val) {
		this.val = val;
		return this;
	}

	public final int val() {
		return this.val;
	}

	public final ClientboundRoomPacket val2(int val2) {
		this.val2 = val2;
		return this;
	}

	public final int val2() {
		return this.val2;
	}

	public final ClientboundRoomPacket val3(String val3) {
		this.val3 = val3;
		return this;
	}

	public final String val3() {
		return this.val3;
	}

	public final ClientboundRoomPacket val4(boolean val4) {
		this.val4 = val4;
		return this;
	}

	public final boolean val4() {
		return this.val4;
	}

	public final ClientboundRoomPacket val5(int val5) {
		this.val5 = val5;
		return this;
	}

	public final int val5() {
		return this.val5;
	}

	public final ClientboundRoomPacket val6(int val6) {
		this.val6 = val6;
		return this;
	}

	public final int val6() {
		return this.val6;
	}

	public final ClientboundRoomPacket val7(int val7) {
		this.val7 = val7;
		return this;
	}

	public final int val7() {
		return this.val7;
	}

	public final ClientboundRoomPacket val8(int val8) {
		this.val8 = val8;
		return this;
	}

	public final int val8() {
		return this.val8;
	}

	public final ClientboundRoomPacket val9(String val9) {
		this.val9 = val9;
		return this;
	}

	public final String val9() {
		return this.val9;
	}

	public final ClientboundRoomPacket val10(int val10) {
		this.val10 = val10;
		return this;
	}

	public final int val10() {
		return this.val10;
	}

	public final ClientboundRoomPacket val11(int val11) {
		this.val11 = val11;
		return this;
	}

	public final int val11() {
		return this.val11;
	}

	public final ClientboundRoomPacket val12(int val12) {
		this.val12 = val12;
		return this;
	}

	public final int val12() {
		return this.val12;
	}

	public final ClientboundRoomPacket val13(int val13) {
		this.val13 = val13;
		return this;
	}

	public final int val13() {
		return this.val13;
	}

	public final ClientboundRoomPacket val14(int val14) {
		this.val14 = val14;
		return this;
	}

	public final int val14() {
		return this.val14;
	}

	public final ClientboundRoomPacket val15(int val15) {
		this.val15 = val15;
		return this;
	}

	public final int val15() {
		return this.val15;
	}

	public final ClientboundRoomPacket val16(int val16) {
		this.val16 = val16;
		return this;
	}

	public final int val16() {
		return this.val16;
	}

	public final ClientboundRoomPacket val17(boolean val17) {
		this.val17 = val17;
		return this;
	}

	public final boolean val17() {
		return this.val17;
	}

	public final ClientboundRoomPacket val18(boolean val18) {
		this.val18 = val18;
		return this;
	}

	public final boolean val18() {
		return this.val18;
	}

	public final ClientboundRoomPacket val19(boolean val19) {
		this.val19 = val19;
		return this;
	}

	public final boolean val19() {
		return this.val19;
	}

	public final ClientboundRoomPacket val20(int val20) {
		this.val20 = val20;
		return this;
	}

	public final int val20() {
		return this.val20;
	}

	public final ClientboundRoomPacket val21(int val21) {
		this.val21 = val21;
		return this;
	}

	public final int val21() {
		return this.val21;
	}

	@Override
	public int packetId() {
		return 470;
	}

	@Override
	public final void write(PacketBuf buf) {
		buf.writeInt(this.val);
		buf.writeInt(this.val2);
		buf.writeString(this.val3);
		buf.writeBoolean(this.val4);
		buf.writeInt(this.val5);
		buf.writeInt(this.val6);
		buf.writeInt(this.val7);
		buf.writeInt(this.val8);
		buf.writeString(this.val9);
		buf.writeInt(this.val10);
		buf.writeInt(this.val11);
		buf.writeInt(this.val12);
		buf.writeInt(this.val13);
		buf.writeInt(this.val14);
		buf.writeInt(this.val15);
		buf.writeInt(this.val16);
		buf.writeBoolean(this.val17);
		buf.writeBoolean(this.val18);
		buf.writeBoolean(this.val19);
		buf.writeInt(this.val20);
		buf.writeInt(this.val21);
	}
}