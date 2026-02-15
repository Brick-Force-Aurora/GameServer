package de.brickforceaurora.server.net.protocol.clientbound.original;

import de.brickforceaurora.server.net.protocol.IClientboundPacket;
import de.brickforceaurora.server.net.protocol.PacketBuf;

public final class ClientboundBungeeStatusPacket implements IClientboundPacket {

	@Override
	public int packetId() {
		return 507;
	}

	@Override
	public final void write(PacketBuf buf) {
	}
}