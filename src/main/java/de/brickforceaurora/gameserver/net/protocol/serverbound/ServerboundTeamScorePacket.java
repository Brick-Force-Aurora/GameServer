package de.brickforceaurora.gameserver.net.protocol.serverbound;

import de.brickforceaurora.gameserver.net.protocol.IServerboundPacket;
import io.netty.buffer.ByteBuf;

public final class ServerboundTeamScorePacket implements IServerboundPacket {

	@Override
	public int packetId() {
		return 75;
	}

	@Override
	public final void read(ByteBuf buffer) {
	}
}