package de.brickforceaurora.gameserver.net.protocol.serverbound;

import de.brickforceaurora.gameserver.net.protocol.IServerboundPacket;
import io.netty.buffer.ByteBuf;
import java.nio.charset.StandardCharsets;

public final class ServerboundChgMapIntroPacket implements IServerboundPacket {

	private int clientId;
	private String intro;

	public final ServerboundChgMapIntroPacket clientId(int clientId) {
		this.clientId = clientId;
		return this;
	}

	public final int clientId() {
		return this.clientId;
	}

	public final ServerboundChgMapIntroPacket intro(String intro) {
		this.intro = intro;
		return this;
	}

	public final String intro() {
		return this.intro;
	}

	@Override
	public int packetId() {
		return 441;
	}

	@Override
	public final void read(ByteBuf buffer) {
		this.clientId = buffer.readIntLE();
		{
			int length = buffer.readIntLE();
			if (length == 0) {
				this.intro = "";
			} else {
				byte[] bytes = new byte[length];
				buffer.readBytes(bytes);
				this.intro = new String(bytes, StandardCharsets.UTF_16LE);
			}
		}
	}
}