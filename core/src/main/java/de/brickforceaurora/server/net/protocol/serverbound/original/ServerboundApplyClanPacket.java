package de.brickforceaurora.server.net.protocol.serverbound.original;

import de.brickforceaurora.server.net.protocol.IServerboundPacket;
import de.brickforceaurora.server.net.protocol.PacketBuf;

public final class ServerboundApplyClanPacket implements IServerboundPacket {

	private int clan;
	private String clanName;

	public final ServerboundApplyClanPacket clan(int clan) {
		this.clan = clan;
		return this;
	}

	public final int clan() {
		return this.clan;
	}

	public final ServerboundApplyClanPacket clanName(String clanName) {
		this.clanName = clanName;
		return this;
	}

	public final String clanName() {
		return this.clanName;
	}

	@Override
	public int packetId() {
		return 221;
	}

	@Override
	public final void read(PacketBuf buf) {
		this.clan = buf.readInt();
		this.clanName = buf.readString();
	}
}