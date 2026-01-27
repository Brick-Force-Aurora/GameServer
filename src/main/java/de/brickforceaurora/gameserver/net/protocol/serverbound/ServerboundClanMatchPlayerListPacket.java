package de.brickforceaurora.gameserver.net.protocol.serverbound;

import de.brickforceaurora.gameserver.net.protocol.IServerboundPacket;
import io.netty.buffer.ByteBuf;

public final class ServerboundClanMatchPlayerListPacket implements IServerboundPacket {

	private long clanMatch;

	public final ServerboundClanMatchPlayerListPacket clanMatch(long clanMatch) {
		this.clanMatch = clanMatch;
		return this;
	}

	public final long clanMatch() {
		return this.clanMatch;
	}

	@Override
	public int packetId() {
		return 270;
	}

	@Override
	public final void read(ByteBuf buffer) {
		this.clanMatch = buffer.readLongLE();
	}
}