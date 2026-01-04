package de.brickforceaurora.gameserver.net.protocol.serverbound;

import de.brickforceaurora.gameserver.net.protocol.IServerboundPacket;
import io.netty.buffer.ByteBuf;

public final class ServerboundStartPacket implements IServerboundPacket {

	private int remain;

	public final ServerboundStartPacket remain(int remain) {
		this.remain = remain;
		return this;
	}

	public final int remain() {
		return this.remain;
	}

	@Override
	public int packetId() {
		return 49;
	}

	@Override
	public final void read(ByteBuf buffer) {
		this.remain = buffer.readIntLE();
	}
}