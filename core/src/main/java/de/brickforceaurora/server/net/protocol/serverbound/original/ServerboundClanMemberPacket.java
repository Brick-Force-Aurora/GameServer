package de.brickforceaurora.server.net.protocol.serverbound.original;

import de.brickforceaurora.server.net.protocol.IServerboundPacket;
import de.brickforceaurora.server.net.protocol.PacketBuf;

public final class ServerboundClanMemberPacket implements IServerboundPacket {

	private int player;
	private int clan;

	public final ServerboundClanMemberPacket player(int player) {
		this.player = player;
		return this;
	}

	public final int player() {
		return this.player;
	}

	public final ServerboundClanMemberPacket clan(int clan) {
		this.clan = clan;
		return this;
	}

	public final int clan() {
		return this.clan;
	}

	@Override
	public int packetId() {
		return 233;
	}

	@Override
	public final void read(PacketBuf buf) {
		this.player = buf.readInt();
		this.clan = buf.readInt();
	}
}