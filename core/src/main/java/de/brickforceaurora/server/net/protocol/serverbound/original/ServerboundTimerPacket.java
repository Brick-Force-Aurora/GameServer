package de.brickforceaurora.server.net.protocol.serverbound.original;

import de.brickforceaurora.server.net.protocol.IServerboundPacket;
import de.brickforceaurora.server.net.protocol.PacketBuf;

public final class ServerboundTimerPacket implements IServerboundPacket {

	private int remainTime;
	private int playTime;

	public final ServerboundTimerPacket remainTime(int remainTime) {
		this.remainTime = remainTime;
		return this;
	}

	public final int remainTime() {
		return this.remainTime;
	}

	public final ServerboundTimerPacket playTime(int playTime) {
		this.playTime = playTime;
		return this;
	}

	public final int playTime() {
		return this.playTime;
	}

	@Override
	public int packetId() {
		return 65;
	}

	@Override
	public final void read(PacketBuf buf) {
		this.remainTime = buf.readInt();
		this.playTime = buf.readInt();
	}
}