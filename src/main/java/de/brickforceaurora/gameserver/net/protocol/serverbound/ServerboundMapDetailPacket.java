package de.brickforceaurora.gameserver.net.protocol.serverbound;

import de.brickforceaurora.gameserver.net.protocol.IServerboundPacket;
import io.netty.buffer.ByteBuf;

public final class ServerboundMapDetailPacket implements IServerboundPacket {

	private int mapSeq;

	public final ServerboundMapDetailPacket mapSeq(int mapSeq) {
		this.mapSeq = mapSeq;
		return this;
	}

	public final int mapSeq() {
		return this.mapSeq;
	}

	@Override
	public int packetId() {
		return 437;
	}

	@Override
	public final void read(ByteBuf buffer) {
		this.mapSeq = buffer.readIntLE();
	}
}