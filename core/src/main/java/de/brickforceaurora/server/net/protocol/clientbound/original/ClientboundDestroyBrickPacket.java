package de.brickforceaurora.server.net.protocol.clientbound.original;

import de.brickforceaurora.server.net.protocol.IClientboundPacket;
import de.brickforceaurora.server.net.protocol.PacketBuf;

public final class ClientboundDestroyBrickPacket implements IClientboundPacket {

	private int brick;

	public final ClientboundDestroyBrickPacket brick(int brick) {
		this.brick = brick;
		return this;
	}

	public final int brick() {
		return this.brick;
	}

	@Override
	public int packetId() {
		return 77;
	}

	@Override
	public final void write(PacketBuf buf) {
		buf.writeInt(this.brick);
	}
}
