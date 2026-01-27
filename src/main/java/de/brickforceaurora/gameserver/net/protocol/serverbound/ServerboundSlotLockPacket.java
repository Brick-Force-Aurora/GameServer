package de.brickforceaurora.gameserver.net.protocol.serverbound;

import de.brickforceaurora.gameserver.net.protocol.IServerboundPacket;
import io.netty.buffer.ByteBuf;

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
	public final void read(ByteBuf buffer) {
		this.slot = buffer.readByte();
		this.lck = buffer.readByte();
	}
}