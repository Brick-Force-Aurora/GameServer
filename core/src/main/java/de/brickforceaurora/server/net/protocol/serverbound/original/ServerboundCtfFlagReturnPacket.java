package de.brickforceaurora.server.net.protocol.serverbound.original;

import de.brickforceaurora.server.net.protocol.IServerboundPacket;
import de.brickforceaurora.server.net.protocol.PacketBuf;

public final class ServerboundCtfFlagReturnPacket implements IServerboundPacket {

	private float x;
	private float y;
	private float z;

	public final ServerboundCtfFlagReturnPacket x(float x) {
		this.x = x;
		return this;
	}

	public final float x() {
		return this.x;
	}

	public final ServerboundCtfFlagReturnPacket y(float y) {
		this.y = y;
		return this;
	}

	public final float y() {
		return this.y;
	}

	public final ServerboundCtfFlagReturnPacket z(float z) {
		this.z = z;
		return this;
	}

	public final float z() {
		return this.z;
	}

	@Override
	public int packetId() {
		return 366;
	}

	@Override
	public final void read(PacketBuf buf) {
		this.x = buf.readFloat();
		this.y = buf.readFloat();
		this.z = buf.readFloat();
	}
}