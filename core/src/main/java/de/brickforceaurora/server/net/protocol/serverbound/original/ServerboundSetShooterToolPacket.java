package de.brickforceaurora.server.net.protocol.serverbound.original;

import de.brickforceaurora.server.net.protocol.IServerboundPacket;
import de.brickforceaurora.server.net.protocol.PacketBuf;

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
	public final void read(PacketBuf buf) {
		this.slot = buf.readByte();
		this.tool = buf.readLong();
	}
}