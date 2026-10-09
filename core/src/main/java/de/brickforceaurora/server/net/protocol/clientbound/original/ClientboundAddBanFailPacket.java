package de.brickforceaurora.server.net.protocol.clientbound.original;

import de.brickforceaurora.server.net.protocol.IClientboundPacket;
import de.brickforceaurora.server.net.protocol.PacketBuf;

public final class ClientboundAddBanFailPacket implements IClientboundPacket {

	@Override
	public int packetId() {
		return 115;
	}

	@Override
	public final void write(PacketBuf buf) {
		buf.writeInt(0); //unused
	}
}