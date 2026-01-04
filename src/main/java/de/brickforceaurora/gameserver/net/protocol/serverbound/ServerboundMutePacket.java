package de.brickforceaurora.gameserver.net.protocol.serverbound;

import de.brickforceaurora.gameserver.net.protocol.IServerboundPacket;
import io.netty.buffer.ByteBuf;
import java.nio.charset.StandardCharsets;

public final class ServerboundMutePacket implements IServerboundPacket {

	private String nickname;
	private int howlong;

	public final ServerboundMutePacket nickname(String nickname) {
		this.nickname = nickname;
		return this;
	}

	public final String nickname() {
		return this.nickname;
	}

	public final ServerboundMutePacket howlong(int howlong) {
		this.howlong = howlong;
		return this;
	}

	public final int howlong() {
		return this.howlong;
	}

	@Override
	public int packetId() {
		return 455;
	}

	@Override
	public final void read(ByteBuf buffer) {
		{
			int length = buffer.readIntLE();
			if (length == 0) {
				this.nickname = "";
			} else {
				byte[] bytes = new byte[length];
				buffer.readBytes(bytes);
				this.nickname = new String(bytes, StandardCharsets.UTF_16LE);
			}
		}
		this.howlong = buffer.readIntLE();
	}
}