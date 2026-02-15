package de.brickforceaurora.server.net.protocol.serverbound.original;

import de.brickforceaurora.server.net.protocol.IServerboundPacket;
import de.brickforceaurora.server.net.protocol.PacketBuf;

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
	public final void read(PacketBuf buf) {
		this.redPoint = buf.readInt();
		this.bluePoint = buf.readInt();
	}
}