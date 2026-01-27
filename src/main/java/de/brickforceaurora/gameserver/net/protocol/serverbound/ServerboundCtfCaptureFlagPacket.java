package de.brickforceaurora.gameserver.net.protocol.serverbound;

import de.brickforceaurora.gameserver.net.protocol.IServerboundPacket;
import io.netty.buffer.ByteBuf;

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
	public final void read(ByteBuf buffer) {
		this.flag = buffer.readIntLE();
		this.opponent = buffer.readBoolean();
	}
}