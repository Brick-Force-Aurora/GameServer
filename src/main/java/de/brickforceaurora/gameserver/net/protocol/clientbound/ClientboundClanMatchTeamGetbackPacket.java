package de.brickforceaurora.gameserver.net.protocol.clientbound;

import de.brickforceaurora.gameserver.net.protocol.IClientboundPacket;
import io.netty.buffer.ByteBuf;

public final class ClientboundClanMatchTeamGetbackPacket implements IClientboundPacket {

	@Override
	public int packetId() {
		return 276;
	}

	@Override
	public final void write(ByteBuf buffer) {
	}
}