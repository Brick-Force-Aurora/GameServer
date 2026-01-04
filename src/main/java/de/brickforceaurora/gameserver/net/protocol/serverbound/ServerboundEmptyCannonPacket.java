package de.brickforceaurora.gameserver.net.protocol.serverbound;

import de.brickforceaurora.gameserver.net.protocol.IServerboundPacket;
import io.netty.buffer.ByteBuf;

public final class ServerboundEmptyCannonPacket implements IServerboundPacket {

	private int cannon;

	public final ServerboundEmptyCannonPacket cannon(int cannon) {
		this.cannon = cannon;
		return this;
	}

	public final int cannon() {
		return this.cannon;
	}

	@Override
	public int packetId() {
		return 160;
	}

	@Override
	public final void read(ByteBuf buffer) {
		this.cannon = buffer.readIntLE();
	}
}