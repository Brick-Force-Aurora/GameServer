package de.brickforceaurora.server.net.protocol.clientbound.original;

import de.brickforceaurora.server.net.protocol.IClientboundPacket;
import de.brickforceaurora.server.net.protocol.PacketBuf;

public final class ClientboundShooterToolPacket implements IClientboundPacket {

	private byte slot;
	private long item;

	public final ClientboundShooterToolPacket slot(byte slot) {
		this.slot = slot;
		return this;
	}

	public final byte slot() {
		return this.slot;
	}

	public final ClientboundShooterToolPacket item(long item) {
		this.item = item;
		return this;
	}

	public final long item() {
		return this.item;
	}

	@Override
	public int packetId() {
		return 332;
	}

	@Override
	public final void write(PacketBuf buf) {
		buf.writeByte(this.slot);
		buf.writeLong(this.item);
	}
}