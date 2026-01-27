package de.brickforceaurora.gameserver.net.protocol.clientbound;

import de.brickforceaurora.gameserver.net.protocol.IClientboundPacket;
import io.netty.buffer.ByteBuf;
import java.nio.charset.StandardCharsets;

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
	public final void write(ByteBuf buffer) {
		buffer.writeIntLE(this.Unnamed0);
		if (this.val2.isEmpty()) {
			buffer.writeIntLE(0);
		} else {
			byte[] bytes = this.val2.getBytes(StandardCharsets.UTF_16LE);
			buffer.writeIntLE(bytes.length);
			buffer.writeBytes(bytes);
		}
		buffer.writeIntLE(this.val3);
		buffer.writeIntLE(this.val4);
		buffer.writeIntLE(this.val5);
		if (this.val6.isEmpty()) {
			buffer.writeIntLE(0);
		} else {
			byte[] bytes = this.val6.getBytes(StandardCharsets.UTF_16LE);
			buffer.writeIntLE(bytes.length);
			buffer.writeBytes(bytes);
		}
		if (this.val7.isEmpty()) {
			buffer.writeIntLE(0);
		} else {
			byte[] bytes = this.val7.getBytes(StandardCharsets.UTF_16LE);
			buffer.writeIntLE(bytes.length);
			buffer.writeBytes(bytes);
		}
		buffer.writeByte(this.val8 & 0xFF);
	}
}