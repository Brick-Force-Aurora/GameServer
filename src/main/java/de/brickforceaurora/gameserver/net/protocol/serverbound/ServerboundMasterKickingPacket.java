package de.brickforceaurora.gameserver.net.protocol.serverbound;

import de.brickforceaurora.gameserver.net.protocol.IServerboundPacket;
import io.netty.buffer.ByteBuf;

public final class ServerboundMasterKickingPacket implements IServerboundPacket {

	private int countdown;

	public final ServerboundMasterKickingPacket countdown(int countdown) {
		this.countdown = countdown;
		return this;
	}

	public final int countdown() {
		return this.countdown;
	}

	@Override
	public int packetId() {
		return 534;
	}

	@Override
	public final void read(ByteBuf buffer) {
		this.countdown = buffer.readIntLE();
	}
}