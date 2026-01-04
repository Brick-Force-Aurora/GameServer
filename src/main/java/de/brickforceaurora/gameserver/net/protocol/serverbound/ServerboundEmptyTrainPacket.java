package de.brickforceaurora.gameserver.net.protocol.serverbound;

import de.brickforceaurora.gameserver.net.protocol.IServerboundPacket;
import io.netty.buffer.ByteBuf;

public final class ServerboundEmptyTrainPacket implements IServerboundPacket {

	private int trainSeq;

	public final ServerboundEmptyTrainPacket trainSeq(int trainSeq) {
		this.trainSeq = trainSeq;
		return this;
	}

	public final int trainSeq() {
		return this.trainSeq;
	}

	@Override
	public int packetId() {
		return 553;
	}

	@Override
	public final void read(ByteBuf buffer) {
		this.trainSeq = buffer.readIntLE();
	}
}