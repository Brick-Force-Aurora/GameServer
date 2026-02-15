package de.brickforceaurora.server.net.protocol.serverbound.original;

import de.brickforceaurora.server.net.protocol.IServerboundPacket;
import de.brickforceaurora.server.net.protocol.PacketBuf;

public final class ServerboundSlotLockPacket implements IServerboundPacket {

	private byte slot;
	private byte lck;

	public final ServerboundSlotLockPacket slot(byte slot) {
		this.slot = slot;
		return this;
	}

	public final byte slot() {
		return this.slot;
	}

	public final ServerboundSlotLockPacket lck(byte lck) {
		this.lck = lck;
		return this;
	}

	public final byte lck() {
		return this.lck;
	}

	@Override
	public int packetId() {
		return 85;
	}

	@Override
	public final void read(PacketBuf buf) {
		this.slot = buf.readByte();
		this.lck = buf.readByte();
	}
}