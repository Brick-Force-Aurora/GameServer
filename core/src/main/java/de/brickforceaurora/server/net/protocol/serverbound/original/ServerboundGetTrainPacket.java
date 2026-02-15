package de.brickforceaurora.server.net.protocol.serverbound.original;

import de.brickforceaurora.server.net.protocol.IServerboundPacket;
import de.brickforceaurora.server.net.protocol.PacketBuf;

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
	public final void read(PacketBuf buf) {
		this.trainSeq = buf.readInt();
		this.trainID = buf.readInt();
	}
}