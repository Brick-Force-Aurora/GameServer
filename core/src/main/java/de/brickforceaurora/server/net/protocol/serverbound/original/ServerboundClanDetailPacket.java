package de.brickforceaurora.server.net.protocol.serverbound.original;

import de.brickforceaurora.server.net.protocol.IServerboundPacket;
import de.brickforceaurora.server.net.protocol.PacketBuf;

public final class ServerboundClanDetailPacket implements IServerboundPacket {

	private int clan;

	public final ServerboundClanDetailPacket clan(int clan) {
		this.clan = clan;
		return this;
	}

	public final int clan() {
		return this.clan;
	}

	@Override
	public int packetId() {
		return 227;
	}

	@Override
	public final void read(PacketBuf buf) {
		this.clan = buf.readInt();
	}
}