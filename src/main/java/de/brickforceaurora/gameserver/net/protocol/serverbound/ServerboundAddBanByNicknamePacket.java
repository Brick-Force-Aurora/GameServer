package de.brickforceaurora.gameserver.net.protocol.serverbound;

import de.brickforceaurora.gameserver.net.protocol.IServerboundPacket;
import io.netty.buffer.ByteBuf;
import java.nio.charset.StandardCharsets;

public final class ServerboundAddBanByNicknamePacket implements IServerboundPacket {

	private String nickname;

	public final ServerboundAddBanByNicknamePacket nickname(String nickname) {
		this.nickname = nickname;
		return this;
	}

	public final String nickname() {
		return this.nickname;
	}

	@Override
	public int packetId() {
		return 112;
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
	}
}