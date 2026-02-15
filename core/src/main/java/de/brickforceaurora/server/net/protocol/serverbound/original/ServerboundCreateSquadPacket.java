package de.brickforceaurora.server.net.protocol.serverbound.original;

import de.brickforceaurora.server.net.protocol.IServerboundPacket;
import de.brickforceaurora.server.net.protocol.PacketBuf;

public final class ServerboundCreateSquadPacket implements IServerboundPacket {

	private int clan;
	private int wannaPlanyMap;
	private int wannaPlayMode;
	private int maxMember;

	public final ServerboundCreateSquadPacket clan(int clan) {
		this.clan = clan;
		return this;
	}

	public final int clan() {
		return this.clan;
	}

	public final ServerboundCreateSquadPacket wannaPlanyMap(int wannaPlanyMap) {
		this.wannaPlanyMap = wannaPlanyMap;
		return this;
	}

	public final int wannaPlanyMap() {
		return this.wannaPlanyMap;
	}

	public final ServerboundCreateSquadPacket wannaPlayMode(int wannaPlayMode) {
		this.wannaPlayMode = wannaPlayMode;
		return this;
	}

	public final int wannaPlayMode() {
		return this.wannaPlayMode;
	}

	public final ServerboundCreateSquadPacket maxMember(int maxMember) {
		this.maxMember = maxMember;
		return this;
	}

	public final int maxMember() {
		return this.maxMember;
	}

	@Override
	public int packetId() {
		return 237;
	}

	@Override
	public final void read(PacketBuf buf) {
		this.clan = buf.readInt();
		this.wannaPlanyMap = buf.readInt();
		this.wannaPlayMode = buf.readInt();
		this.maxMember = buf.readInt();
	}
}