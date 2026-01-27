package de.brickforceaurora.gameserver.net.protocol.serverbound;

import de.brickforceaurora.gameserver.net.protocol.IServerboundPacket;
import io.netty.buffer.ByteBuf;

public final class ServerboundSetShooterToolPacket implements IServerboundPacket {

	private byte slot;
	private long tool;

	public final ServerboundSetShooterToolPacket slot(byte slot) {
		this.slot = slot;
		return this;
	}

	public final byte slot() {
		return this.slot;
	}

	public final ServerboundSetShooterToolPacket tool(long tool) {
		this.tool = tool;
		return this;
	}

	public final long tool() {
		return this.tool;
	}

	@Override
	public int packetId() {
		return 333;
	}

	@Override
	public final void read(ByteBuf buffer) {
		this.slot = buffer.readByte();
		this.tool = buffer.readLongLE();
	}
}