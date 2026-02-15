package de.brickforceaurora.server.net.protocol.clientbound.original;

import de.brickforceaurora.server.net.protocol.IClientboundPacket;
import de.brickforceaurora.server.net.protocol.PacketBuf;

public final class ClientboundEscapeStatusPacket implements IClientboundPacket {

	@Override
	public int packetId() {
		return 522;
	}

	@Override
	public final void write(PacketBuf buf) {
	}
}