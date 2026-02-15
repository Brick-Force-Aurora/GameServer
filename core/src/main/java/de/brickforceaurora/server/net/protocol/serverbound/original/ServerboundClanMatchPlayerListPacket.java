package de.brickforceaurora.server.net.protocol.serverbound.original;

import de.brickforceaurora.server.net.protocol.IServerboundPacket;
import de.brickforceaurora.server.net.protocol.PacketBuf;

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
	public final void read(PacketBuf buf) {
		this.clanMatch = buf.readLong();
	}
}