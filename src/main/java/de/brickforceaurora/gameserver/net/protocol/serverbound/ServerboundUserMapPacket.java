package de.brickforceaurora.gameserver.net.protocol.serverbound;

import de.brickforceaurora.gameserver.net.protocol.IServerboundPacket;
import io.netty.buffer.ByteBuf;

public final class ServerboundUserMapPacket implements IServerboundPacket {

	private int page;

	public final ServerboundUserMapPacket page(int page) {
		this.page = page;
		return this;
	}

	public final int page() {
		return this.page;
	}

	@Override
	public int packetId() {
		return 429;
	}

	@Override
	public final void read(ByteBuf buffer) {
		this.page = buffer.readIntLE();
	}
}