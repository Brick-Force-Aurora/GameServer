package de.brickforceaurora.gameserver.net.protocol.serverbound;

import de.brickforceaurora.gameserver.net.protocol.IServerboundPacket;
import io.netty.buffer.ByteBuf;

public final class ServerboundLeaveSquadPacket implements IServerboundPacket {

	@Override
	public int packetId() {
		return 241;
	}

	@Override
	public final void read(ByteBuf buffer) {
	}
}