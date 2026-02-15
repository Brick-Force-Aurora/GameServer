package de.brickforceaurora.server.net.protocol.serverbound.original;

import de.brickforceaurora.server.net.protocol.IServerboundPacket;
import de.brickforceaurora.server.net.protocol.PacketBuf;

public final class ServerboundSetStatusPacket implements IServerboundPacket {

	private int status;

	public final ServerboundSetStatusPacket status(int status) {
		this.status = status;
		return this;
	}

	public final int status() {
		return this.status;
	}

	@Override
	public int packetId() {
		return 47;
	}

	@Override
	public final void read(PacketBuf buf) {
		this.status = buf.readInt();
	}
}