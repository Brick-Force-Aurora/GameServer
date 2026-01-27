package de.brickforceaurora.gameserver.net.protocol.serverbound;

import de.brickforceaurora.gameserver.net.protocol.IServerboundPacket;
import io.netty.buffer.ByteBuf;
import java.nio.charset.StandardCharsets;

public final class ServerboundChangeClanNoticePacket implements IServerboundPacket {

	private int clan;
	private String notice;

	public final ServerboundChangeClanNoticePacket clan(int clan) {
		this.clan = clan;
		return this;
	}

	public final int clan() {
		return this.clan;
	}

	public final ServerboundChangeClanNoticePacket notice(String notice) {
		this.notice = notice;
		return this;
	}

	public final String notice() {
		return this.notice;
	}

	@Override
	public int packetId() {
		return 225;
	}

	@Override
	public final void read(ByteBuf buffer) {
		this.clan = buffer.readIntLE();
		{
			int length = buffer.readIntLE();
			if (length == 0) {
				this.notice = "";
			} else {
				byte[] bytes = new byte[length];
				buffer.readBytes(bytes);
				this.notice = new String(bytes, StandardCharsets.UTF_16LE);
			}
		}
	}
}