package de.brickforceaurora.gameserver.net.protocol.serverbound;

import de.brickforceaurora.gameserver.net.protocol.IServerboundPacket;
import io.netty.buffer.ByteBuf;

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
	public final void read(ByteBuf buffer) {
		this.yes = buffer.readBoolean();
	}
}