package de.brickforceaurora.gameserver.net.protocol.serverbound;

import de.brickforceaurora.gameserver.net.protocol.IServerboundPacket;
import io.netty.buffer.ByteBuf;

public final class ServerboundCtfPickFlagPacket implements IServerboundPacket {

	private int flag;

	public final ServerboundCtfPickFlagPacket flag(int flag) {
		this.flag = flag;
		return this;
	}

	public final int flag() {
		return this.flag;
	}

	@Override
	public int packetId() {
		return 285;
	}

	@Override
	public final void read(ByteBuf buffer) {
		this.flag = buffer.readIntLE();
	}
}