package de.brickforceaurora.gameserver.net.protocol.serverbound;

import de.brickforceaurora.gameserver.net.protocol.IServerboundPacket;
import io.netty.buffer.ByteBuf;

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
	public final void read(ByteBuf buffer) {
		this.player = buffer.readIntLE();
		this.clan = buffer.readIntLE();
	}
}