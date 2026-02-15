package de.brickforceaurora.server.net.protocol.serverbound.original;

import de.brickforceaurora.server.net.protocol.IServerboundPacket;
import de.brickforceaurora.server.net.protocol.PacketBuf;

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
	public final void read(PacketBuf buf) {
		this.repeat = buf.readInt();
		this.isBuildPhase = buf.readBoolean();
	}
}