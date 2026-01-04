package de.brickforceaurora.gameserver.net.protocol.clientbound;

import de.brickforceaurora.gameserver.net.protocol.IClientboundPacket;
import io.netty.buffer.ByteBuf;
import java.nio.charset.StandardCharsets;

public final class ClientboundRegMapInfoPacket implements IClientboundPacket {

	private int val;
	private String val2;
	private String val3;
	private int val4;
	private int val5;
	private int val6;
	private int val7;
	private byte val8;
	private byte val9;
	private byte val10;
	private byte val11;
	private byte val12;
	private int val13;
	private int val14;
	private int val15;
	private int val16;
	private int val17;
	private int val18;

	public final ClientboundRegMapInfoPacket val(int val) {
		this.val = val;
		return this;
	}

	public final int val() {
		return this.val;
	}

	public final ClientboundRegMapInfoPacket val2(String val2) {
		this.val2 = val2;
		return this;
	}

	public final String val2() {
		return this.val2;
	}

	public final ClientboundRegMapInfoPacket val3(String val3) {
		this.val3 = val3;
		return this;
	}

	public final String val3() {
		return this.val3;
	}

	public final ClientboundRegMapInfoPacket val4(int val4) {
		if (val4 > 32767L || val4 < 0L) {
			throw new IllegalArgumentException(
					"Value " + val4 + " is out of bounds of allowed number range of 0 - 32767");
		}
		this.val4 = val4;
		return this;
	}

	public final int val4() {
		return this.val4;
	}

	public final ClientboundRegMapInfoPacket val5(int val5) {
		if (val5 > 255L || val5 < 0L) {
			throw new IllegalArgumentException(
					"Value " + val5 + " is out of bounds of allowed number range of 0 - 255");
		}
		this.val5 = val5;
		return this;
	}

	public final int val5() {
		return this.val5;
	}

	public final ClientboundRegMapInfoPacket val6(int val6) {
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

	public final ClientboundRegMapInfoPacket val7(int val7) {
		this.val7 = val7;
		return this;
	}

	public final int val7() {
		return this.val7;
	}

	public final ClientboundRegMapInfoPacket val8(byte val8) {
		this.val8 = val8;
		return this;
	}

	public final byte val8() {
		return this.val8;
	}

	public final ClientboundRegMapInfoPacket val9(byte val9) {
		this.val9 = val9;
		return this;
	}

	public final byte val9() {
		return this.val9;
	}

	public final ClientboundRegMapInfoPacket val10(byte val10) {
		this.val10 = val10;
		return this;
	}

	public final byte val10() {
		return this.val10;
	}

	public final ClientboundRegMapInfoPacket val11(byte val11) {
		this.val11 = val11;
		return this;
	}

	public final byte val11() {
		return this.val11;
	}

	public final ClientboundRegMapInfoPacket val12(byte val12) {
		this.val12 = val12;
		return this;
	}

	public final byte val12() {
		return this.val12;
	}

	public final ClientboundRegMapInfoPacket val13(int val13) {
		this.val13 = val13;
		return this;
	}

	public final int val13() {
		return this.val13;
	}

	public final ClientboundRegMapInfoPacket val14(int val14) {
		this.val14 = val14;
		return this;
	}

	public final int val14() {
		return this.val14;
	}

	public final ClientboundRegMapInfoPacket val15(int val15) {
		this.val15 = val15;
		return this;
	}

	public final int val15() {
		return this.val15;
	}

	public final ClientboundRegMapInfoPacket val16(int val16) {
		this.val16 = val16;
		return this;
	}

	public final int val16() {
		return this.val16;
	}

	public final ClientboundRegMapInfoPacket val17(int val17) {
		this.val17 = val17;
		return this;
	}

	public final int val17() {
		return this.val17;
	}

	public final ClientboundRegMapInfoPacket val18(int val18) {
		this.val18 = val18;
		return this;
	}

	public final int val18() {
		return this.val18;
	}

	@Override
	public int packetId() {
		return 61;
	}

	@Override
	public final void write(ByteBuf buffer) {
		buffer.writeIntLE(this.val);
		if (this.val2.isEmpty()) {
			buffer.writeIntLE(0);
		} else {
			byte[] bytes = this.val2.getBytes(StandardCharsets.UTF_16LE);
			buffer.writeIntLE(bytes.length);
			buffer.writeBytes(bytes);
		}
		if (this.val3.isEmpty()) {
			buffer.writeIntLE(0);
		} else {
			byte[] bytes = this.val3.getBytes(StandardCharsets.UTF_16LE);
			buffer.writeIntLE(bytes.length);
			buffer.writeBytes(bytes);
		}
		buffer.writeShortLE(this.val4 & 0xFFFF);
		buffer.writeByte(this.val5 & 0xFF);
		buffer.writeByte(this.val6 & 0xFF);
		buffer.writeIntLE(this.val7);
		buffer.writeByte(this.val8);
		buffer.writeByte(this.val9);
		buffer.writeByte(this.val10);
		buffer.writeByte(this.val11);
		buffer.writeByte(this.val12);
		buffer.writeIntLE(this.val13);
		buffer.writeIntLE(this.val14);
		buffer.writeIntLE(this.val15);
		buffer.writeIntLE(this.val16);
		buffer.writeIntLE(this.val17);
		buffer.writeIntLE(this.val18);
	}
}