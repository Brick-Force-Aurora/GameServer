package de.brickforceaurora.server.net.protocol.serverbound.original;

import de.brickforceaurora.server.net.protocol.IServerboundPacket;
import de.brickforceaurora.server.net.protocol.PacketBuf;

public final class ServerboundCtfPickFlagPacket implements IServerboundPacket {

	private int flag;

	public final ServerboundCtfPickFlagPacket flag(int flag) {
		this.flag = flag;
		return this;
	}

	public final int flag() {
		return this.flag;
	}

	@Override
	public int packetId() {
		return 285;
	}

	@Override
	public final void read(PacketBuf buf) {
		this.flag = buf.readInt();
	}
}