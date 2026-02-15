package de.brickforceaurora.server.net.protocol.serverbound.original;

import de.brickforceaurora.server.net.protocol.IServerboundPacket;
import de.brickforceaurora.server.net.protocol.PacketBuf;

public final class ServerboundChgCountryFilterPacket implements IServerboundPacket {

	private int country;

	public final ServerboundChgCountryFilterPacket country(int country) {
		this.country = country;
		return this;
	}

	public final int country() {
		return this.country;
	}

	@Override
	public int packetId() {
		return 348;
	}

	@Override
	public final void read(PacketBuf buf) {
		this.country = buf.readInt();
	}
}