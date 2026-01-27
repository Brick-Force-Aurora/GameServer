package de.brickforceaurora.gameserver.net.protocol.serverbound;

import de.brickforceaurora.gameserver.net.protocol.IServerboundPacket;
import io.netty.buffer.ByteBuf;

public final class ServerboundKickSquadPacket implements IServerboundPacket {

	private int kicked;

	public final ServerboundKickSquadPacket kicked(int kicked) {
		this.kicked = kicked;
		return this;
	}

	public final int kicked() {
		return this.kicked;
	}

	@Override
	public int packetId() {
		return 257;
	}

	@Override
	public final void read(ByteBuf buffer) {
		this.kicked = buffer.readIntLE();
	}
}