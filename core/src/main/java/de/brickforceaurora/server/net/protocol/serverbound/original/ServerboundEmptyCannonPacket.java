package de.brickforceaurora.server.net.protocol.serverbound.original;

import de.brickforceaurora.server.net.protocol.IServerboundPacket;
import de.brickforceaurora.server.net.protocol.PacketBuf;

public final class ServerboundEmptyCannonPacket implements IServerboundPacket {

	private int cannon;

	public final ServerboundEmptyCannonPacket cannon(int cannon) {
		this.cannon = cannon;
		return this;
	}

	public final int cannon() {
		return this.cannon;
	}

	@Override
	public int packetId() {
		return 160;
	}

	@Override
	public final void read(PacketBuf buf) {
		this.cannon = buf.readInt();
	}
}