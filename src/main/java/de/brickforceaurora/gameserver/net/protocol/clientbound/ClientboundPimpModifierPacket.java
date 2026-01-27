package de.brickforceaurora.gameserver.net.protocol.clientbound;

import de.brickforceaurora.gameserver.net.protocol.IClientboundPacket;
import io.netty.buffer.ByteBuf;

public final class ClientboundPimpModifierPacket implements IClientboundPacket {

	private int val;
	private int val2;
	private float val3;

	public final ClientboundPimpModifierPacket val(int val) {
		this.val = val;
		return this;
	}

	public final int val() {
		return this.val;
	}

	public final ClientboundPimpModifierPacket val2(int val2) {
		this.val2 = val2;
		return this;
	}

	public final int val2() {
		return this.val2;
	}

	public final ClientboundPimpModifierPacket val3(float val3) {
		this.val3 = val3;
		return this;
	}

	public final float val3() {
		return this.val3;
	}

	@Override
	public int packetId() {
		return 356;
	}

	@Override
	public final void write(ByteBuf buffer) {
		buffer.writeIntLE(this.val);
		buffer.writeIntLE(this.val2);
		buffer.writeFloatLE(this.val3);
	}
}