package de.brickforceaurora.server.net.protocol.serverbound.original;

import de.brickforceaurora.server.net.protocol.IServerboundPacket;
import de.brickforceaurora.server.net.protocol.PacketBuf;

public final class ServerboundKickoutVotePacket implements IServerboundPacket {

	private boolean yes;

	public final ServerboundKickoutVotePacket yes(boolean yes) {
		this.yes = yes;
		return this;
	}

	public final boolean yes() {
		return this.yes;
	}

	@Override
	public int packetId() {
		return 496;
	}

	@Override
	public final void read(PacketBuf buf) {
		this.yes = buf.readBoolean();
	}
}