package de.brickforceaurora.gameserver.net.protocol.serverbound;

import de.brickforceaurora.gameserver.net.protocol.IServerboundPacket;
import io.netty.buffer.ByteBuf;

public final class ServerboundResumeRoomPacket implements IServerboundPacket {

	private int nextStatus;

	public final ServerboundResumeRoomPacket nextStatus(int nextStatus) {
		this.nextStatus = nextStatus;
		return this;
	}

	public final int nextStatus() {
		return this.nextStatus;
	}

	@Override
	public int packetId() {
		return 32;
	}

	@Override
	public final void read(ByteBuf buffer) {
		this.nextStatus = buffer.readIntLE();
	}
}