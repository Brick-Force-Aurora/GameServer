package de.brickforceaurora.gameserver.net.protocol.clientbound;

import de.brickforceaurora.gameserver.net.protocol.IClientboundPacket;
import io.netty.buffer.ByteBuf;
import java.nio.charset.StandardCharsets;

public final class ClientboundClanMatchingListPacket implements IClientboundPacket {

	private int val;
	private int val2;
	private String val3;
	private String val4;
	private int val5;
	private int val6;
	private int val7;

	public final ClientboundClanMatchingListPacket val(int val) {
		this.val = val;
		return this;
	}

	public final int val() {
		return this.val;
	}

	public final ClientboundClanMatchingListPacket val2(int val2) {
		this.val2 = val2;
		return this;
	}

	public final int val2() {
		return this.val2;
	}

	public final ClientboundClanMatchingListPacket val3(String val3) {
		this.val3 = val3;
		return this;
	}

	public final String val3() {
		return this.val3;
	}

	public final ClientboundClanMatchingListPacket val4(String val4) {
		this.val4 = val4;
		return this;
	}

	public final String val4() {
		return this.val4;
	}

	public final ClientboundClanMatchingListPacket val5(int val5) {
		this.val5 = val5;
		return this;
	}

	public final int val5() {
		return this.val5;
	}

	public final ClientboundClanMatchingListPacket val6(int val6) {
		this.val6 = val6;
		return this;
	}

	public final int val6() {
		return this.val6;
	}

	public final ClientboundClanMatchingListPacket val7(int val7) {
		this.val7 = val7;
		return this;
	}

	public final int val7() {
		return this.val7;
	}

	@Override
	public int packetId() {
		return 561;
	}

	@Override
	public final void write(ByteBuf buffer) {
		buffer.writeIntLE(this.val);
		buffer.writeIntLE(this.val2);
		if (this.val3.isEmpty()) {
			buffer.writeIntLE(0);
		} else {
			byte[] bytes = this.val3.getBytes(StandardCharsets.UTF_16LE);
			buffer.writeIntLE(bytes.length);
			buffer.writeBytes(bytes);
		}
		if (this.val4.isEmpty()) {
			buffer.writeIntLE(0);
		} else {
			byte[] bytes = this.val4.getBytes(StandardCharsets.UTF_16LE);
			buffer.writeIntLE(bytes.length);
			buffer.writeBytes(bytes);
		}
		buffer.writeIntLE(this.val5);
		buffer.writeIntLE(this.val6);
		buffer.writeIntLE(this.val7);
	}
}