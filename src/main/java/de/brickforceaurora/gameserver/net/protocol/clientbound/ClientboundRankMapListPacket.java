package de.brickforceaurora.gameserver.net.protocol.clientbound;

import de.brickforceaurora.gameserver.net.protocol.IClientboundPacket;
import io.netty.buffer.ByteBuf;

public final class ClientboundRankMapListPacket implements IClientboundPacket {

	@Override
	public int packetId() {
		return 59;
	}

	@Override
	public final void write(ByteBuf buffer) {
	}
}