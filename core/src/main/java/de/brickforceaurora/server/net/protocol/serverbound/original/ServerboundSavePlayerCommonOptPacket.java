package de.brickforceaurora.server.net.protocol.serverbound.original;

import de.brickforceaurora.server.net.protocol.IServerboundPacket;
import de.brickforceaurora.server.net.protocol.PacketBuf;

public final class ServerboundSavePlayerCommonOptPacket implements IServerboundPacket {

	private int commonOpt;

	public final ServerboundSavePlayerCommonOptPacket commonOpt(int commonOpt) {
		this.commonOpt = commonOpt;
		return this;
	}

	public final int commonOpt() {
		return this.commonOpt;
	}

	@Override
	public int packetId() {
		return 460;
	}

	@Override
	public final void read(PacketBuf buf) {
		this.commonOpt = buf.readInt();
	}
}