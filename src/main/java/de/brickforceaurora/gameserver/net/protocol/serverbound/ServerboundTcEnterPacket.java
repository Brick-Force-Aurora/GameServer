package de.brickforceaurora.gameserver.net.protocol.serverbound;

import de.brickforceaurora.gameserver.net.protocol.IServerboundPacket;
import io.netty.buffer.ByteBuf;

public final class ServerboundTcEnterPacket implements IServerboundPacket {

	private int chest;

	public final ServerboundTcEnterPacket chest(int chest) {
		this.chest = chest;
		return this;
	}

	public final int chest() {
		return this.chest;
	}

	@Override
	public int packetId() {
		return 372;
	}

	@Override
	public final void read(ByteBuf buffer) {
		this.chest = buffer.readIntLE();
	}
}