package de.brickforceaurora.server.net.protocol.serverbound.original;

import de.brickforceaurora.server.net.protocol.IServerboundPacket;
import de.brickforceaurora.server.net.protocol.PacketBuf;

public final class ServerboundDestroyClanPacket implements IServerboundPacket {

	private int clan;

	public final ServerboundDestroyClanPacket clan(int clan) {
		this.clan = clan;
		return this;
	}

	public final int clan() {
		return this.clan;
	}

	@Override
	public int packetId() {
		return 191;
	}

	@Override
	public final void read(PacketBuf buf) {
		this.clan = buf.readInt();
	}
}