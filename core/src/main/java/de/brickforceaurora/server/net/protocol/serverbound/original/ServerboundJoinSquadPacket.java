package de.brickforceaurora.server.net.protocol.serverbound.original;

import de.brickforceaurora.server.net.protocol.IServerboundPacket;
import de.brickforceaurora.server.net.protocol.PacketBuf;

public final class ServerboundJoinSquadPacket implements IServerboundPacket {

	private int clan;
	private int index;
	private int squadCounter;

	public final ServerboundJoinSquadPacket clan(int clan) {
		this.clan = clan;
		return this;
	}

	public final int clan() {
		return this.clan;
	}

	public final ServerboundJoinSquadPacket index(int index) {
		this.index = index;
		return this;
	}

	public final int index() {
		return this.index;
	}

	public final ServerboundJoinSquadPacket squadCounter(int squadCounter) {
		this.squadCounter = squadCounter;
		return this;
	}

	public final int squadCounter() {
		return this.squadCounter;
	}

	@Override
	public int packetId() {
		return 239;
	}

	@Override
	public final void read(PacketBuf buf) {
		this.clan = buf.readInt();
		this.index = buf.readInt();
		this.squadCounter = buf.readInt();
	}
}