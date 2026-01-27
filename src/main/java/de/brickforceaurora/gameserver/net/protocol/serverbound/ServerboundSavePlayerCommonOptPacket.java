package de.brickforceaurora.gameserver.net.protocol.serverbound;

import de.brickforceaurora.gameserver.net.protocol.IServerboundPacket;
import io.netty.buffer.ByteBuf;

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
	public final void read(ByteBuf buffer) {
		this.commonOpt = buffer.readIntLE();
	}
}