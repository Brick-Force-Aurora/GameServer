package de.brickforceaurora.server.net.protocol.serverbound.original;

import de.brickforceaurora.server.net.protocol.IServerboundPacket;
import de.brickforceaurora.server.net.protocol.PacketBuf;

public final class ServerboundClanMatchingListPacket implements IServerboundPacket {

	@Override
	public int packetId() {
		return 560;
	}

	@Override
	public final void read(PacketBuf buf) {
	}
}