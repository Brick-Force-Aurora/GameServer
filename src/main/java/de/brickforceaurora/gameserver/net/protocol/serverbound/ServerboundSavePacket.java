package de.brickforceaurora.gameserver.net.protocol.serverbound;

import de.brickforceaurora.gameserver.net.protocol.IServerboundPacket;
import io.netty.buffer.ByteBuf;

public final class ServerboundSavePacket implements IServerboundPacket {

	private int slot;
	final String UnknownValue0 = "thumbnail.Length";
	final String UnknownValue1 = "thumbnail[i]";

	public final ServerboundSavePacket slot(int slot) {
		this.slot = slot;
		return this;
	}

	public final int slot() {
		return this.slot;
	}

	@Override
	public int packetId() {
		return 39;
	}

	@Override
	public final void read(ByteBuf buffer) {
		this.slot = buffer.readIntLE();
	}
}