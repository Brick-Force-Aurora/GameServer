package de.brickforceaurora.server.net.protocol.serverbound.original;

import de.brickforceaurora.server.net.protocol.IServerboundPacket;
import de.brickforceaurora.server.net.protocol.PacketBuf;

public final class ServerboundLogoutPacket implements IServerboundPacket {

	@Override
	public int packetId() {
		return 157;
	}

	@Override
	public final void read(PacketBuf buf) {
	}
}