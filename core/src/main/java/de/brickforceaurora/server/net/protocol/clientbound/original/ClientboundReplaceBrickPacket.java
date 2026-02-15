package de.brickforceaurora.server.net.protocol.clientbound.original;

import de.brickforceaurora.server.net.protocol.IClientboundPacket;
import de.brickforceaurora.server.net.protocol.PacketBuf;

public final class ClientboundReplaceBrickPacket implements IClientboundPacket {

	private int val;
	private int val2;
	private int val3;
	private int val4;
	private int val5;
	private int val6;
	private int val7;
	private int val8;

	public final ClientboundReplaceBrickPacket val(int val) {
		this.val = val;
		return this;
	}

	public final int val() {
		return this.val;
	}

	public final ClientboundReplaceBrickPacket val2(int val2) {
		this.val2 = val2;
		return this;
	}

	public final int val2() {
		return this.val2;
	}

	public final ClientboundReplaceBrickPacket val3(int val3) {
		this.val3 = val3;
		return this;
	}

	public final int val3() {
		return this.val3;
	}

	public final ClientboundReplaceBrickPacket val4(int val4) {
		if (val4 > 255L || val4 < 0L) {
			throw new IllegalArgumentException(
					"Value " + val4 + " is out of bounds of allowed number range of 0 - 255");
		}
		this.val4 = val4;
		return this;
	}

	public final int val4() {
		return this.val4;
	}

	public final ClientboundReplaceBrickPacket val5(int val5) {
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

	public final ClientboundReplaceBrickPacket val6(int val6) {
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

	public final ClientboundReplaceBrickPacket val7(int val7) {
		if (val7 > 255L || val7 < 0L) {
			throw new IllegalArgumentException(
					"Value " + val7 + " is out of bounds of allowed number range of 0 - 255");
		}
		this.val7 = val7;
		return this;
	}

	public final int val7() {
		return this.val7;
	}

	public final ClientboundReplaceBrickPacket val8(int val8) {
		if (val8 > 255L || val8 < 0L) {
			throw new IllegalArgumentException(
					"Value " + val8 + " is out of bounds of allowed number range of 0 - 255");
		}
		this.val8 = val8;
		return this;
	}

	public final int val8() {
		return this.val8;
	}

	@Override
	public int packetId() {
		return 328;
	}

	@Override
	public final void write(PacketBuf buf) {
		buf.writeInt(this.val);
		buf.writeInt(this.val2);
		buf.writeInt(this.val3);
		buf.writeByte(this.val4);
		buf.writeByte(this.val5);
		buf.writeByte(this.val6);
		buf.writeByte(this.val7);
		buf.writeByte(this.val8);
	}
}