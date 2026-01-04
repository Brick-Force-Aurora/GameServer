package de.brickforceaurora.gameserver.net.protocol.clientbound;

import de.brickforceaurora.gameserver.net.protocol.IClientboundPacket;
import io.netty.buffer.ByteBuf;

public final class ClientboundBndShiftPhasePacket implements IClientboundPacket {

	private int repeat;
	private boolean isBuildPhase;

	public final ClientboundBndShiftPhasePacket repeat(int repeat) {
		this.repeat = repeat;
		return this;
	}

	public final int repeat() {
		return this.repeat;
	}

	public final ClientboundBndShiftPhasePacket isBuildPhase(boolean isBuildPhase) {
		this.isBuildPhase = isBuildPhase;
		return this;
	}

	public final boolean isBuildPhase() {
		return this.isBuildPhase;
	}

	@Override
	public int packetId() {
		return 344;
	}

	@Override
	public final void write(ByteBuf buffer) {
		buffer.writeIntLE(this.repeat);
		buffer.writeBoolean(this.isBuildPhase);
	}
}