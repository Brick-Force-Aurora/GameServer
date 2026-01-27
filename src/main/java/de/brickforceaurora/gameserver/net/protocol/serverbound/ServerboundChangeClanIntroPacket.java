package de.brickforceaurora.gameserver.net.protocol.serverbound;

import de.brickforceaurora.gameserver.net.protocol.IServerboundPacket;
import io.netty.buffer.ByteBuf;
import java.nio.charset.StandardCharsets;

public final class ServerboundChangeClanIntroPacket implements IServerboundPacket {

	private int clan;
	private String intro;

	public final ServerboundChangeClanIntroPacket clan(int clan) {
		this.clan = clan;
		return this;
	}

	public final int clan() {
		return this.clan;
	}

	public final ServerboundChangeClanIntroPacket intro(String intro) {
		this.intro = intro;
		return this;
	}

	public final String intro() {
		return this.intro;
	}

	@Override
	public int packetId() {
		return 223;
	}

	@Override
	public final void read(ByteBuf buffer) {
		this.clan = buffer.readIntLE();
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