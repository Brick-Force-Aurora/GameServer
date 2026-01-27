package de.brickforceaurora.gameserver.net.protocol.clientbound;

import de.brickforceaurora.gameserver.net.protocol.IClientboundPacket;
import io.netty.buffer.ByteBuf;

public final class ClientboundWeaponDurabilityPacket implements IClientboundPacket {

	private long val;
	private int val2;

	public final ClientboundWeaponDurabilityPacket val(long val) {
		this.val = val;
		return this;
	}

	public final long val() {
		return this.val;
	}

	public final ClientboundWeaponDurabilityPacket val2(int val2) {
		this.val2 = val2;
		return this;
	}

	public final int val2() {
		return this.val2;
	}

	@Override
	public int packetId() {
		return 350;
	}

	@Override
	public final void write(ByteBuf buffer) {
		buffer.writeLongLE(this.val);
		buffer.writeIntLE(this.val2);
	}
}