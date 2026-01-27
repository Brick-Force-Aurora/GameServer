package de.brickforceaurora.gameserver.net.protocol.clientbound;

import de.brickforceaurora.gameserver.net.protocol.IClientboundPacket;
import io.netty.buffer.ByteBuf;

public final class ClientboundClanApplicantEndPacket implements IClientboundPacket {

	@Override
	public int packetId() {
		return 208;
	}

	@Override
	public final void write(ByteBuf buffer) {
	}
}