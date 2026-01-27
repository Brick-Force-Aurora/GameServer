package de.brickforceaurora.gameserver.net.protocol.clientbound;

import de.brickforceaurora.gameserver.net.protocol.IClientboundPacket;
import io.netty.buffer.ByteBuf;

public final class ClientboundChgDoorStatusPacket implements IClientboundPacket {

	private int val;
	private byte val2;

	public final ClientboundChgDoorStatusPacket val(int val) {
		this.val = val;
		return this;
	}

	public final int val() {
		return this.val;
	}

	public final ClientboundChgDoorStatusPacket val2(byte val2) {
		this.val2 = val2;
		return this;
	}

	public final byte val2() {
		return this.val2;
	}

	@Override
	public int packetId() {
		return 449;
	}

	@Override
	public final void write(ByteBuf buffer) {
		buffer.writeIntLE(this.val);
		buffer.writeByte(this.val2);
	}
}