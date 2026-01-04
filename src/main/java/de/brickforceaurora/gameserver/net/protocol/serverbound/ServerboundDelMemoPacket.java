package de.brickforceaurora.gameserver.net.protocol.serverbound;

import de.brickforceaurora.gameserver.net.protocol.IServerboundPacket;
import io.netty.buffer.ByteBuf;

public final class ServerboundDelMemoPacket implements IServerboundPacket {

	private long seq;

	public final ServerboundDelMemoPacket seq(long seq) {
		this.seq = seq;
		return this;
	}

	public final long seq() {
		return this.seq;
	}

	@Override
	public int packetId() {
		return 130;
	}

	@Override
	public final void read(ByteBuf buffer) {
		this.seq = buffer.readLongLE();
	}
}