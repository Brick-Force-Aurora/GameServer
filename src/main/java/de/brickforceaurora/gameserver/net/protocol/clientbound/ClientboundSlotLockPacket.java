package de.brickforceaurora.gameserver.net.protocol.clientbound;

import de.brickforceaurora.gameserver.net.protocol.IClientboundPacket;
import io.netty.buffer.ByteBuf;

public final class ClientboundSlotLockPacket implements IClientboundPacket {

	private byte val;
	private byte val2;

	public final ClientboundSlotLockPacket val(byte val) {
		this.val = val;
		return this;
	}

	public final byte val() {
		return this.val;
	}

	public final ClientboundSlotLockPacket val2(byte val2) {
		this.val2 = val2;
		return this;
	}

	public final byte val2() {
		return this.val2;
	}

	@Override
	public int packetId() {
		return 86;
	}

	@Override
	public final void write(ByteBuf buffer) {
		buffer.writeByte(this.val);
		buffer.writeByte(this.val2);
	}
}