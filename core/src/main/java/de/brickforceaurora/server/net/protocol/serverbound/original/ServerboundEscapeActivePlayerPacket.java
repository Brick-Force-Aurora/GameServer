package de.brickforceaurora.server.net.protocol.serverbound.original;

import de.brickforceaurora.server.net.protocol.IServerboundPacket;
import de.brickforceaurora.server.net.protocol.PacketBuf;

public final class ServerboundEscapeActivePlayerPacket implements IServerboundPacket {

	private boolean IsActive;

	public final ServerboundEscapeActivePlayerPacket IsActive(boolean IsActive) {
		this.IsActive = IsActive;
		return this;
	}

	public final boolean IsActive() {
		return this.IsActive;
	}

	@Override
	public int packetId() {
		return 525;
	}

	@Override
	public final void read(PacketBuf buf) {
		this.IsActive = buf.readBoolean();
	}
}