package de.brickforceaurora.gameserver.net.protocol.serverbound;

import de.brickforceaurora.gameserver.net.protocol.IServerboundPacket;
import io.netty.buffer.ByteBuf;
import java.nio.charset.StandardCharsets;

public final class ServerboundGmSaysPacket implements IServerboundPacket {

	private String text;

	public final ServerboundGmSaysPacket text(String text) {
		this.text = text;
		return this;
	}

	public final String text() {
		return this.text;
	}

	@Override
	public int packetId() {
		return 186;
	}

	@Override
	public final void read(ByteBuf buffer) {
		{
			int length = buffer.readIntLE();
			if (length == 0) {
				this.text = "";
			} else {
				byte[] bytes = new byte[length];
				buffer.readBytes(bytes);
				this.text = new String(bytes, StandardCharsets.UTF_16LE);
			}
		}
	}
}