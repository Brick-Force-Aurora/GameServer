package de.brickforceaurora.gameserver.net.protocol.serverbound;

import de.brickforceaurora.gameserver.net.protocol.IServerboundPacket;
import io.netty.buffer.ByteBuf;

public final class ServerboundGetTrainPacket implements IServerboundPacket {

	private int trainSeq;
	private int trainID;

	public final ServerboundGetTrainPacket trainSeq(int trainSeq) {
		this.trainSeq = trainSeq;
		return this;
	}

	public final int trainSeq() {
		return this.trainSeq;
	}

	public final ServerboundGetTrainPacket trainID(int trainID) {
		this.trainID = trainID;
		return this;
	}

	public final int trainID() {
		return this.trainID;
	}

	@Override
	public int packetId() {
		return 551;
	}

	@Override
	public final void read(ByteBuf buffer) {
		this.trainSeq = buffer.readIntLE();
		this.trainID = buffer.readIntLE();
	}
}