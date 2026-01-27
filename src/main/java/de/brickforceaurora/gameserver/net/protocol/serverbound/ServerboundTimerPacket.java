package de.brickforceaurora.gameserver.net.protocol.serverbound;

import de.brickforceaurora.gameserver.net.protocol.IServerboundPacket;
import io.netty.buffer.ByteBuf;

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
	public final void read(ByteBuf buffer) {
		this.remainTime = buffer.readIntLE();
		this.playTime = buffer.readIntLE();
	}
}