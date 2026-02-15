package de.brickforceaurora.server.net.protocol.clientbound.original;

import de.brickforceaurora.server.net.protocol.IClientboundPacket;
import de.brickforceaurora.server.net.protocol.PacketBuf;

public final class ClientboundTcChestPacket implements IClientboundPacket {

	private int val;
	private int val2;
	private int val3;
	private int val4;
	private int val5;
	private int val6;
	private int val7;
	private int val8;
	private String val9;
	private int val10;
	private String val11;
	private int val12;
	final String UnknownValue0 = "item.opt";
	private byte val13;

	public final ClientboundTcChestPacket val(int val) {
		this.val = val;
		return this;
	}

	public final int val() {
		return this.val;
	}

	public final ClientboundTcChestPacket val2(int val2) {
		this.val2 = val2;
		return this;
	}

	public final int val2() {
		return this.val2;
	}

	public final ClientboundTcChestPacket val3(int val3) {
		this.val3 = val3;
		return this;
	}

	public final int val3() {
		return this.val3;
	}

	public final ClientboundTcChestPacket val4(int val4) {
		this.val4 = val4;
		return this;
	}

	public final int val4() {
		return this.val4;
	}

	public final ClientboundTcChestPacket val5(int val5) {
		this.val5 = val5;
		return this;
	}

	public final int val5() {
		return this.val5;
	}

	public final ClientboundTcChestPacket val6(int val6) {
		this.val6 = val6;
		return this;
	}

	public final int val6() {
		return this.val6;
	}

	public final ClientboundTcChestPacket val7(int val7) {
		this.val7 = val7;
		return this;
	}

	public final int val7() {
		return this.val7;
	}

	public final ClientboundTcChestPacket val8(int val8) {
		this.val8 = val8;
		return this;
	}

	public final int val8() {
		return this.val8;
	}

	public final ClientboundTcChestPacket val9(String val9) {
		this.val9 = val9;
		return this;
	}

	public final String val9() {
		return this.val9;
	}

	public final ClientboundTcChestPacket val10(int val10) {
		this.val10 = val10;
		return this;
	}

	public final int val10() {
		return this.val10;
	}

	public final ClientboundTcChestPacket val11(String val11) {
		this.val11 = val11;
		return this;
	}

	public final String val11() {
		return this.val11;
	}

	public final ClientboundTcChestPacket val12(int val12) {
		this.val12 = val12;
		return this;
	}

	public final int val12() {
		return this.val12;
	}

	public final ClientboundTcChestPacket val13(byte val13) {
		this.val13 = val13;
		return this;
	}

	public final byte val13() {
		return this.val13;
	}

	@Override
	public int packetId() {
		return 375;
	}

	@Override
	public final void write(PacketBuf buf) {
		buf.writeInt(this.val);
		buf.writeInt(this.val2);
		buf.writeInt(this.val3);
		buf.writeInt(this.val4);
		buf.writeInt(this.val5);
		buf.writeInt(this.val6);
		buf.writeInt(this.val7);
		buf.writeInt(this.val8);
		buf.writeString(this.val9);
		buf.writeInt(this.val10);
		buf.writeString(this.val11);
		buf.writeInt(this.val12);
		buf.writeByte(this.val13);
	}
}