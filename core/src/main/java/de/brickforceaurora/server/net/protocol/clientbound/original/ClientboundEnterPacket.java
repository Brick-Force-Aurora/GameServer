package de.brickforceaurora.server.net.protocol.clientbound.original;

import de.brickforceaurora.server.net.protocol.IClientboundPacket;
import de.brickforceaurora.server.net.protocol.PacketBuf;

public final class ClientboundEnterPacket implements IClientboundPacket {

	private int val;
	private String val2;
	private String val3;
	private int val4;
	private String val5;
	private int val6;
	private int val7;
	final String UnknownValue0 = "array[i]";
	private int val8;
	private int val9;
	private int val10;
	private String val11;
	private int val12;
	private int val13;
	private int val14;
	final String UnknownValue1 = "val7";
	final String UnknownValue2 = "array2[j]";
	final String UnknownValue3 = "val7";
	final String UnknownValue4 = "array3[k]";

	public final ClientboundEnterPacket val(int val) {
		this.val = val;
		return this;
	}

	public final int val() {
		return this.val;
	}

	public final ClientboundEnterPacket val2(String val2) {
		this.val2 = val2;
		return this;
	}

	public final String val2() {
		return this.val2;
	}

	public final ClientboundEnterPacket val3(String val3) {
		this.val3 = val3;
		return this;
	}

	public final String val3() {
		return this.val3;
	}

	public final ClientboundEnterPacket val4(int val4) {
		this.val4 = val4;
		return this;
	}

	public final int val4() {
		return this.val4;
	}

	public final ClientboundEnterPacket val5(String val5) {
		this.val5 = val5;
		return this;
	}

	public final String val5() {
		return this.val5;
	}

	public final ClientboundEnterPacket val6(int val6) {
		this.val6 = val6;
		return this;
	}

	public final int val6() {
		return this.val6;
	}

	public final ClientboundEnterPacket val7(int val7) {
		this.val7 = val7;
		return this;
	}

	public final int val7() {
		return this.val7;
	}

	public final ClientboundEnterPacket val8(int val8) {
		this.val8 = val8;
		return this;
	}

	public final int val8() {
		return this.val8;
	}

	public final ClientboundEnterPacket val9(int val9) {
		this.val9 = val9;
		return this;
	}

	public final int val9() {
		return this.val9;
	}

	public final ClientboundEnterPacket val10(int val10) {
		this.val10 = val10;
		return this;
	}

	public final int val10() {
		return this.val10;
	}

	public final ClientboundEnterPacket val11(String val11) {
		this.val11 = val11;
		return this;
	}

	public final String val11() {
		return this.val11;
	}

	public final ClientboundEnterPacket val12(int val12) {
		this.val12 = val12;
		return this;
	}

	public final int val12() {
		return this.val12;
	}

	public final ClientboundEnterPacket val13(int val13) {
		this.val13 = val13;
		return this;
	}

	public final int val13() {
		return this.val13;
	}

	public final ClientboundEnterPacket val14(int val14) {
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

	@Override
	public int packetId() {
		return 10;
	}

	@Override
	public final void write(PacketBuf buf) {
		buf.writeInt(this.val);
		buf.writeString(this.val2);
		buf.writeString(this.val3);
		buf.writeInt(this.val4);
		buf.writeString(this.val5);
		buf.writeInt(this.val6);
		buf.writeInt(this.val7);
		buf.writeInt(this.val8);
		buf.writeInt(this.val9);
		buf.writeInt(this.val10);
		buf.writeString(this.val11);
		buf.writeInt(this.val12);
		buf.writeInt(this.val13);
		buf.writeByte(this.val14);
	}
}