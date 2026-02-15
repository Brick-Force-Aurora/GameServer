package de.brickforceaurora.server.net.protocol.serverbound.original;

import de.brickforceaurora.server.net.protocol.IServerboundPacket;
import de.brickforceaurora.server.net.protocol.PacketBuf;

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
	public final void read(PacketBuf buf) {
		this.nextStatus = buf.readInt();
	}
}