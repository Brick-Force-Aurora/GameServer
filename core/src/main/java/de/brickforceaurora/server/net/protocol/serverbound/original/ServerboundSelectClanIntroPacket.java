package de.brickforceaurora.server.net.protocol.serverbound.original;

import de.brickforceaurora.server.net.protocol.IServerboundPacket;
import de.brickforceaurora.server.net.protocol.PacketBuf;

public final class ServerboundSelectClanIntroPacket implements IServerboundPacket {

	private int clan;

	public final ServerboundSelectClanIntroPacket clan(int clan) {
		this.clan = clan;
		return this;
	}

	public final int clan() {
		return this.clan;
	}

	@Override
	public int packetId() {
		return 203;
	}

	@Override
	public final void read(PacketBuf buf) {
		this.clan = buf.readInt();
	}
}