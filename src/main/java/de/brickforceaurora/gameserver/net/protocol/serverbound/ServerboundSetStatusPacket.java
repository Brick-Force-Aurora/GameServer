package de.brickforceaurora.gameserver.net.protocol.serverbound;

import de.brickforceaurora.gameserver.net.protocol.IServerboundPacket;
import io.netty.buffer.ByteBuf;

public final class ServerboundSetStatusPacket implements IServerboundPacket {

	private int status;

	public final ServerboundSetStatusPacket status(int status) {
		this.status = status;
		return this;
	}

	public final int status() {
		return this.status;
	}

	@Override
	public int packetId() {
		return 47;
	}

	@Override
	public final void read(ByteBuf buffer) {
		this.status = buffer.readIntLE();
	}
}