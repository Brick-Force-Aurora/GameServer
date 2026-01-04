package de.brickforceaurora.gameserver.net.protocol.serverbound;

import de.brickforceaurora.gameserver.net.protocol.IServerboundPacket;
import io.netty.buffer.ByteBuf;
import java.nio.charset.StandardCharsets;

public final class ServerboundCheckClanNameAvailabilityPacket implements IServerboundPacket {

	private String clanName;

	public final ServerboundCheckClanNameAvailabilityPacket clanName(String clanName) {
		this.clanName = clanName;
		return this;
	}

	public final String clanName() {
		return this.clanName;
	}

	@Override
	public int packetId() {
		return 187;
	}

	@Override
	public final void read(ByteBuf buffer) {
		{
			int length = buffer.readIntLE();
			if (length == 0) {
				this.clanName = "";
			} else {
				byte[] bytes = new byte[length];
				buffer.readBytes(bytes);
				this.clanName = new String(bytes, StandardCharsets.UTF_16LE);
			}
		}
	}
}