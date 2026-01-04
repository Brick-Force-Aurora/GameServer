package de.brickforceaurora.gameserver.net.protocol.serverbound;

import de.brickforceaurora.gameserver.net.protocol.IServerboundPacket;
import io.netty.buffer.ByteBuf;

public final class ServerboundXtrapPacket implements IServerboundPacket {

	final String UnknownValue0 = "step3Buffer[i]";
	private int val;

	public final ServerboundXtrapPacket val(int val) {
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
		return 162;
	}

	@Override
	public final void read(ByteBuf buffer) {
		this.val = buffer.readUnsignedByte();
	}
}