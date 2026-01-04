package de.brickforceaurora.gameserver.net.protocol.clientbound;

import de.brickforceaurora.gameserver.net.protocol.IClientboundPacket;
import io.netty.buffer.ByteBuf;

public final class ClientboundMatchTeamCanceledPacket implements IClientboundPacket {

	@Override
	public int packetId() {
		return 277;
	}

	@Override
	public final void write(ByteBuf buffer) {
	}
}