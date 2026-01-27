package de.brickforceaurora.gameserver.net.protocol.serverbound;

import de.brickforceaurora.gameserver.net.protocol.IServerboundPacket;
import io.netty.buffer.ByteBuf;

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
	public final void read(ByteBuf buffer) {
		this.clan = buffer.readIntLE();
		this.wannaPlanyMap = buffer.readIntLE();
		this.wannaPlayMode = buffer.readIntLE();
		this.maxMember = buffer.readIntLE();
	}
}