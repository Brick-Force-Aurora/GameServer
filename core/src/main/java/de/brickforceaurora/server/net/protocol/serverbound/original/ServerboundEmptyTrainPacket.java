package de.brickforceaurora.server.net.protocol.serverbound.original;

import de.brickforceaurora.server.net.protocol.IServerboundPacket;
import de.brickforceaurora.server.net.protocol.PacketBuf;

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
	public final void read(PacketBuf buf) {
		this.trainSeq = buf.readInt();
	}
}