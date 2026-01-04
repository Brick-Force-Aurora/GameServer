package de.brickforceaurora.gameserver.net.protocol.serverbound;

import de.brickforceaurora.gameserver.net.protocol.IServerboundPacket;
import io.netty.buffer.ByteBuf;

public final class ServerboundMatchRestartCountPacket implements IServerboundPacket {

	private int count;

	public final ServerboundMatchRestartCountPacket count(int count) {
		this.count = count;
		return this;
	}

	public final int count() {
		return this.count;
	}

	@Override
	public int packetId() {
		return 264;
	}

	@Override
	public final void read(ByteBuf buffer) {
		this.count = buffer.readIntLE();
	}
}