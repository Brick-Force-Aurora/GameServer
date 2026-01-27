package de.brickforceaurora.gameserver.net.protocol.clientbound;

import de.brickforceaurora.gameserver.net.protocol.IClientboundPacket;
import io.netty.buffer.ByteBuf;
import java.nio.charset.StandardCharsets;

public final class ClientboundNoticePacket implements IClientboundPacket {

	private String val;
	private byte val2;

	public final ClientboundNoticePacket val(String val) {
		this.val = val;
		return this;
	}

	public final String val() {
		return this.val;
	}

	public final ClientboundNoticePacket val2(byte val2) {
		this.val2 = val2;
		return this;
	}

	public final byte val2() {
		return this.val2;
	}

	@Override
	public int packetId() {
		return 346;
	}

	@Override
	public final void write(ByteBuf buffer) {
		if (this.val.isEmpty()) {
			buffer.writeIntLE(0);
		} else {
			byte[] bytes = this.val.getBytes(StandardCharsets.UTF_16LE);
			buffer.writeIntLE(bytes.length);
			buffer.writeBytes(bytes);
		}
		buffer.writeByte(this.val2);
	}
}