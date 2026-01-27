package de.brickforceaurora.gameserver.net.protocol.serverbound;

import de.brickforceaurora.gameserver.net.protocol.IServerboundPacket;
import io.netty.buffer.ByteBuf;

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
	public final void read(ByteBuf buffer) {
		this.x = buffer.readFloatLE();
		this.y = buffer.readFloatLE();
		this.z = buffer.readFloatLE();
	}
}