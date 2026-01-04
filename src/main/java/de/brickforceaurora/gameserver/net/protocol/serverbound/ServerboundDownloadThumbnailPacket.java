package de.brickforceaurora.gameserver.net.protocol.serverbound;

import de.brickforceaurora.gameserver.net.protocol.IServerboundPacket;
import io.netty.buffer.ByteBuf;

public final class ServerboundDownloadThumbnailPacket implements IServerboundPacket {

	final String UnknownValue0 = "(byte)(isUserMap?1:0)";
	private int clientId;

	public final ServerboundDownloadThumbnailPacket clientId(int clientId) {
		this.clientId = clientId;
		return this;
	}

	public final int clientId() {
		return this.clientId;
	}

	@Override
	public int packetId() {
		return 100;
	}

	@Override
	public final void read(ByteBuf buffer) {
		this.clientId = buffer.readIntLE();
	}
}