package de.brickforceaurora.gameserver.net.protocol.serverbound;

import de.brickforceaurora.gameserver.net.protocol.IServerboundPacket;
import io.netty.buffer.ByteBuf;

public final class ServerboundAddBanPacket implements IServerboundPacket {

	private int banWannabe;

	public final ServerboundAddBanPacket banWannabe(int banWannabe) {
		this.banWannabe = banWannabe;
		return this;
	}

	public final int banWannabe() {
		return this.banWannabe;
	}

	@Override
	public int packetId() {
		return 105;
	}

	@Override
	public final void read(ByteBuf buffer) {
		this.banWannabe = buffer.readIntLE();
	}
}