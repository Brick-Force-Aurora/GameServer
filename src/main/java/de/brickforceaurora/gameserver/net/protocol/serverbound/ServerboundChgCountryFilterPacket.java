package de.brickforceaurora.gameserver.net.protocol.serverbound;

import de.brickforceaurora.gameserver.net.protocol.IServerboundPacket;
import io.netty.buffer.ByteBuf;

public final class ServerboundChgCountryFilterPacket implements IServerboundPacket {

	private int country;

	public final ServerboundChgCountryFilterPacket country(int country) {
		this.country = country;
		return this;
	}

	public final int country() {
		return this.country;
	}

	@Override
	public int packetId() {
		return 348;
	}

	@Override
	public final void read(ByteBuf buffer) {
		this.country = buffer.readIntLE();
	}
}