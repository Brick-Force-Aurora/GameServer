package de.brickforceaurora.server.net.protocol.clientbound.original;

import de.brickforceaurora.server.net.protocol.IClientboundPacket;
import de.brickforceaurora.server.net.protocol.PacketBuf;

public final class ClientboundDestroyedBrickPacket implements IClientboundPacket {

	private int brick;

	public final ClientboundDestroyedBrickPacket brick(int brick) {
		this.brick = brick;
		return this;
	}

	public final int brick() {
		return this.brick;
	}

	@Override
	public int packetId() {
		return 78;
	}

	@Override
	public final void write(PacketBuf buf) {
		buf.writeInt(this.brick);
	}
}
