package de.brickforceaurora.gameserver.net.protocol.serverbound;

import de.brickforceaurora.gameserver.net.protocol.IServerboundPacket;
import io.netty.buffer.ByteBuf;

public final class ServerboundLoadCompletePacket implements IServerboundPacket {

	private int crc;

	public final ServerboundLoadCompletePacket crc(int crc) {
		this.crc = crc;
		return this;
	}

	public final int crc() {
		return this.crc;
	}

	@Override
	public int packetId() {
		return 42;
	}

	@Override
	public final void read(ByteBuf buffer) {
		this.crc = buffer.readIntLE();
	}
}