package de.brickforceaurora.server.net.protocol.clientbound.original;

import de.brickforceaurora.server.net.protocol.IClientboundPacket;
import de.brickforceaurora.server.net.protocol.PacketBuf;

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
	public final void write(PacketBuf buf) {
		buf.writeInt(this.repeat);
		buf.writeBoolean(this.isBuildPhase);
	}
}