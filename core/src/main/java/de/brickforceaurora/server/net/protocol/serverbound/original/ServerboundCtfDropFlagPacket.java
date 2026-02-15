package de.brickforceaurora.server.net.protocol.serverbound.original;

import de.brickforceaurora.server.net.protocol.IServerboundPacket;
import de.brickforceaurora.server.net.protocol.PacketBuf;

public final class ServerboundCtfDropFlagPacket implements IServerboundPacket {

	private float x;
	private float y;
	private float z;

	public final ServerboundCtfDropFlagPacket x(float x) {
		this.x = x;
		return this;
	}

	public final float x() {
		return this.x;
	}

	public final ServerboundCtfDropFlagPacket y(float y) {
		this.y = y;
		return this;
	}

	public final float y() {
		return this.y;
	}

	public final ServerboundCtfDropFlagPacket z(float z) {
		this.z = z;
		return this;
	}

	public final float z() {
		return this.z;
	}

	@Override
	public int packetId() {
		return 289;
	}

	@Override
	public final void read(PacketBuf buf) {
		this.x = buf.readFloat();
		this.y = buf.readFloat();
		this.z = buf.readFloat();
	}
}