package de.brickforceaurora.gameserver.net.protocol.clientbound;

import de.brickforceaurora.gameserver.net.protocol.IClientboundPacket;
import io.netty.buffer.ByteBuf;
import java.nio.charset.StandardCharsets;

public final class ClientboundResetMyClanPacket implements IClientboundPacket {

	private int val;
	private String val2;
	private int val3;
	private int val4;

	public final ClientboundResetMyClanPacket val(int val) {
		this.val = val;
		return this;
	}

	public final int val() {
		return this.val;
	}

	public final ClientboundResetMyClanPacket val2(String val2) {
		this.val2 = val2;
		return this;
	}

	public final String val2() {
		return this.val2;
	}

	public final ClientboundResetMyClanPacket val3(int val3) {
		this.val3 = val3;
		return this;
	}

	public final int val3() {
		return this.val3;
	}

	public final ClientboundResetMyClanPacket val4(int val4) {
		this.val4 = val4;
		return this;
	}

	public final int val4() {
		return this.val4;
	}

	@Override
	public int packetId() {
		return 197;
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
		buffer.writeIntLE(this.val3);
		buffer.writeIntLE(this.val4);
	}
}