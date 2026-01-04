package de.brickforceaurora.gameserver.net.protocol.serverbound;

import de.brickforceaurora.gameserver.net.protocol.IServerboundPacket;
import io.netty.buffer.ByteBuf;

public final class ServerboundMissionPointPacket implements IServerboundPacket {

	private int redPoint;
	private int bluePoint;

	public final ServerboundMissionPointPacket redPoint(int redPoint) {
		this.redPoint = redPoint;
		return this;
	}

	public final int redPoint() {
		return this.redPoint;
	}

	public final ServerboundMissionPointPacket bluePoint(int bluePoint) {
		this.bluePoint = bluePoint;
		return this;
	}

	public final int bluePoint() {
		return this.bluePoint;
	}

	@Override
	public int packetId() {
		return 508;
	}

	@Override
	public final void read(ByteBuf buffer) {
		this.redPoint = buffer.readIntLE();
		this.bluePoint = buffer.readIntLE();
	}
}