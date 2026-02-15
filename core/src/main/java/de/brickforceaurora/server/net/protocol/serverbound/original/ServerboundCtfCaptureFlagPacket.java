package de.brickforceaurora.server.net.protocol.serverbound.original;

import de.brickforceaurora.server.net.protocol.IServerboundPacket;
import de.brickforceaurora.server.net.protocol.PacketBuf;

public final class ServerboundCtfCaptureFlagPacket implements IServerboundPacket {

	private int flag;
	private boolean opponent;

	public final ServerboundCtfCaptureFlagPacket flag(int flag) {
		this.flag = flag;
		return this;
	}

	public final int flag() {
		return this.flag;
	}

	public final ServerboundCtfCaptureFlagPacket opponent(boolean opponent) {
		this.opponent = opponent;
		return this;
	}

	public final boolean opponent() {
		return this.opponent;
	}

	@Override
	public int packetId() {
		return 287;
	}

	@Override
	public final void read(PacketBuf buf) {
		this.flag = buf.readInt();
		this.opponent = buf.readBoolean();
	}
}