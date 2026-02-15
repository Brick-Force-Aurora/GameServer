package de.brickforceaurora.server.net.protocol.serverbound.original;

import de.brickforceaurora.server.net.protocol.IServerboundPacket;
import de.brickforceaurora.server.net.protocol.PacketBuf;

public final class ServerboundCheckTermItemPacket implements IServerboundPacket {

	private long expiring;
	private long alter;

	public final ServerboundCheckTermItemPacket expiring(long expiring) {
		this.expiring = expiring;
		return this;
	}

	public final long expiring() {
		return this.expiring;
	}

	public final ServerboundCheckTermItemPacket alter(long alter) {
		this.alter = alter;
		return this;
	}

	public final long alter() {
		return this.alter;
	}

	@Override
	public int packetId() {
		return 123;
	}

	@Override
	public final void read(PacketBuf buf) {
		this.expiring = buf.readLong();
		this.alter = buf.readLong();
	}
}