package de.brickforceaurora.gameserver.net.protocol.clientbound;

import de.brickforceaurora.gameserver.net.protocol.IClientboundPacket;
import io.netty.buffer.ByteBuf;

public final class ClientboundChgMapIntroPacket implements IClientboundPacket {

	private int Unnamed0;

	public final ClientboundChgMapIntroPacket Unnamed0(int Unnamed0) {
		this.Unnamed0 = Unnamed0;
		return this;
	}

	public final int Unnamed0() {
		return this.Unnamed0;
	}

	@Override
	public int packetId() {
		return 442;
	}

	@Override
	public final void write(ByteBuf buffer) {
		buffer.writeIntLE(this.Unnamed0);
	}
}