package de.brickforceaurora.gameserver.net.protocol.serverbound;

import de.brickforceaurora.gameserver.net.protocol.IServerboundPacket;
import io.netty.buffer.ByteBuf;
import java.nio.charset.StandardCharsets;

public final class ServerboundHashCheckPacket implements IServerboundPacket {

	private String hash;

	public final ServerboundHashCheckPacket hash(String hash) {
		this.hash = hash;
		return this;
	}

	public final String hash() {
		return this.hash;
	}

	@Override
	public int packetId() {
		return 395;
	}

	@Override
	public final void read(ByteBuf buffer) {
		{
			int length = buffer.readIntLE();
			if (length == 0) {
				this.hash = "";
			} else {
				byte[] bytes = new byte[length];
				buffer.readBytes(bytes);
				this.hash = new String(bytes, StandardCharsets.UTF_16LE);
			}
		}
	}
}