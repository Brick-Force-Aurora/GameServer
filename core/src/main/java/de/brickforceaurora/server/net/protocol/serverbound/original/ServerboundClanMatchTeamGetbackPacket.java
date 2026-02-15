package de.brickforceaurora.server.net.protocol.serverbound.original;

import de.brickforceaurora.server.net.protocol.IServerboundPacket;
import de.brickforceaurora.server.net.protocol.PacketBuf;

public final class ServerboundClanMatchTeamGetbackPacket implements IServerboundPacket {

	private int clan;
	private int index;

	public final ServerboundClanMatchTeamGetbackPacket clan(int clan) {
		this.clan = clan;
		return this;
	}

	public final int clan() {
		return this.clan;
	}

	public final ServerboundClanMatchTeamGetbackPacket index(int index) {
		this.index = index;
		return this;
	}

	public final int index() {
		return this.index;
	}

	@Override
	public int packetId() {
		return 275;
	}

	@Override
	public final void read(PacketBuf buf) {
		this.clan = buf.readInt();
		this.index = buf.readInt();
	}
}