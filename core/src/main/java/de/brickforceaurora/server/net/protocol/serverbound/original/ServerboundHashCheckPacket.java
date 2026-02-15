package de.brickforceaurora.server.net.protocol.serverbound.original;

import de.brickforceaurora.server.net.protocol.IServerboundPacket;
import de.brickforceaurora.server.net.protocol.PacketBuf;

public final class ServerboundHashCheckPacket implements IServerboundPacket {

	private String hash;

	public final ServerboundHashCheckPacket hash(String hash) {
		this.hash = hash;
		return this;
	}

	public final String hash() {
		return this.hash;
	}

	@Override
	public int packetId() {
		return 395;
	}

	@Override
	public final void read(PacketBuf buf) {
		this.hash = buf.readString();
	}
}