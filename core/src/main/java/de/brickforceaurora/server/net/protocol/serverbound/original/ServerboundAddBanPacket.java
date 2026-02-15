package de.brickforceaurora.server.net.protocol.serverbound.original;

import de.brickforceaurora.server.net.protocol.IServerboundPacket;
import de.brickforceaurora.server.net.protocol.PacketBuf;

public final class ServerboundAddBanPacket implements IServerboundPacket {

	private int banWannabe;

	public final ServerboundAddBanPacket banWannabe(int banWannabe) {
		this.banWannabe = banWannabe;
		return this;
	}

	public final int banWannabe() {
		return this.banWannabe;
	}

	@Override
	public int packetId() {
		return 105;
	}

	@Override
	public final void read(PacketBuf buf) {
		this.banWannabe = buf.readInt();
	}
}