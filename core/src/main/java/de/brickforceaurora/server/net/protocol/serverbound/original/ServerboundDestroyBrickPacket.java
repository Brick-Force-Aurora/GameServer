package de.brickforceaurora.server.net.protocol.serverbound.original;

import de.brickforceaurora.server.net.protocol.IServerboundPacket;
import de.brickforceaurora.server.net.protocol.PacketBuf;

public final class ServerboundDestroyBrickPacket implements IServerboundPacket {

	private int brick;

	public final ServerboundDestroyBrickPacket brick(int brick) {
		this.brick = brick;
		return this;
	}

	public final int brick() {
		return this.brick;
	}

	@Override
	public int packetId() {
		return 76;
	}

	@Override
	public final void read(PacketBuf buf) {
		this.brick = buf.readInt();
	}
}