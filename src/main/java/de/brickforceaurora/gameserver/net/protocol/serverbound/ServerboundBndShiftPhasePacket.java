package de.brickforceaurora.gameserver.net.protocol.serverbound;

import de.brickforceaurora.gameserver.net.protocol.IServerboundPacket;
import io.netty.buffer.ByteBuf;

public final class ServerboundBndShiftPhasePacket implements IServerboundPacket {

	private int repeat;
	private boolean isBuildPhase;

	public final ServerboundBndShiftPhasePacket repeat(int repeat) {
		this.repeat = repeat;
		return this;
	}

	public final int repeat() {
		return this.repeat;
	}

	public final ServerboundBndShiftPhasePacket isBuildPhase(boolean isBuildPhase) {
		this.isBuildPhase = isBuildPhase;
		return this;
	}

	public final boolean isBuildPhase() {
		return this.isBuildPhase;
	}

	@Override
	public int packetId() {
		return 349;
	}

	@Override
	public final void read(ByteBuf buffer) {
		this.repeat = buffer.readIntLE();
		this.isBuildPhase = buffer.readBoolean();
	}
}