package de.brickforceaurora.server.net.protocol.clientbound.original;

import de.brickforceaurora.server.net.protocol.IClientboundPacket;
import de.brickforceaurora.server.net.protocol.PacketBuf;

public final class ClientboundMapDetailPacket implements IClientboundPacket {

	private int Unnamed0;
	private String val2;
	private int val3;
	private int val4;
	private int val5;
	private String val6;
	private String val7;
	private int val8;

	public final ClientboundMapDetailPacket Unnamed0(int Unnamed0) {
		this.Unnamed0 = Unnamed0;
		return this;
	}

	public final int Unnamed0() {
		return this.Unnamed0;
	}

	public final ClientboundMapDetailPacket val2(String val2) {
		this.val2 = val2;
		return this;
	}

	public final String val2() {
		return this.val2;
	}

	public final ClientboundMapDetailPacket val3(int val3) {
		this.val3 = val3;
		return this;
	}

	public final int val3() {
		return this.val3;
	}

	public final ClientboundMapDetailPacket val4(int val4) {
		this.val4 = val4;
		return this;
	}

	public final int val4() {
		return this.val4;
	}

	public final ClientboundMapDetailPacket val5(int val5) {
		this.val5 = val5;
		return this;
	}

	public final int val5() {
		return this.val5;
	}

	public final ClientboundMapDetailPacket val6(String val6) {
		this.val6 = val6;
		return this;
	}

	public final String val6() {
		return this.val6;
	}

	public final ClientboundMapDetailPacket val7(String val7) {
		this.val7 = val7;
		return this;
	}

	public final String val7() {
		return this.val7;
	}

	public final ClientboundMapDetailPacket val8(int val8) {
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
		return 438;
	}

	@Override
	public final void write(PacketBuf buf) {
		buf.writeInt(this.Unnamed0);
		buf.writeString(this.val2);
		buf.writeInt(this.val3);
		buf.writeInt(this.val4);
		buf.writeInt(this.val5);
		buf.writeString(this.val6);
		buf.writeString(this.val7);
		buf.writeByte(this.val8);
	}
}