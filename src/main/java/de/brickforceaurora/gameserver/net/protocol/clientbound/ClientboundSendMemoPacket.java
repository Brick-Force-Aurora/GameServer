package de.brickforceaurora.gameserver.net.protocol.clientbound;

import de.brickforceaurora.gameserver.net.protocol.IClientboundPacket;
import io.netty.buffer.ByteBuf;
import java.nio.charset.StandardCharsets;

public final class ClientboundSendMemoPacket implements IClientboundPacket {

	private long val;
	private String val2;

	public final ClientboundSendMemoPacket val(long val) {
		this.val = val;
		return this;
	}

	public final long val() {
		return this.val;
	}

	public final ClientboundSendMemoPacket val2(String val2) {
		this.val2 = val2;
		return this;
	}

	public final String val2() {
		return this.val2;
	}

	@Override
	public int packetId() {
		return 127;
	}

	@Override
	public final void write(ByteBuf buffer) {
		buffer.writeLongLE(this.val);
		if (this.val2.isEmpty()) {
			buffer.writeIntLE(0);
		} else {
			byte[] bytes = this.val2.getBytes(StandardCharsets.UTF_16LE);
			buffer.writeIntLE(bytes.length);
			buffer.writeBytes(bytes);
		}
	}
}