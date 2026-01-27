package de.brickforceaurora.gameserver.net.protocol.clientbound;

import de.brickforceaurora.gameserver.net.protocol.IClientboundPacket;
import io.netty.buffer.ByteBuf;
import java.nio.charset.StandardCharsets;

public final class ClientboundClanChangeRoomNamePacket implements IClientboundPacket {

	private String val;

	public final ClientboundClanChangeRoomNamePacket val(String val) {
		this.val = val;
		return this;
	}

	public final String val() {
		return this.val;
	}

	@Override
	public int packetId() {
		return 564;
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
	}
}