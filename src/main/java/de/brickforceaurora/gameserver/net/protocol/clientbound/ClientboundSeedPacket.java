package de.brickforceaurora.gameserver.net.protocol.clientbound;

import de.brickforceaurora.gameserver.net.protocol.IClientboundPacket;
import io.netty.buffer.ByteBuf;

public final class ClientboundSeedPacket implements IClientboundPacket {

	private int val;

	public final ClientboundSeedPacket val(int val) {
		if (val > 255L || val < 0L) {
			throw new IllegalArgumentException("Value " + val + " is out of bounds of allowed number range of 0 - 255");
		}
		this.val = val;
		return this;
	}

	public final int val() {
		return this.val;
	}

	@Override
	public int packetId() {
		return 84;
	}

	@Override
	public final void write(ByteBuf buffer) {
		buffer.writeByte(this.val & 0xFF);
	}
}