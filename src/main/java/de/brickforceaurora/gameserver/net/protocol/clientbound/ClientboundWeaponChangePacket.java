package de.brickforceaurora.gameserver.net.protocol.clientbound;

import de.brickforceaurora.gameserver.net.protocol.IClientboundPacket;
import io.netty.buffer.ByteBuf;

public final class ClientboundWeaponChangePacket implements IClientboundPacket {

	private int val;
	private int Unnamed0;
	private long val3;

	public final ClientboundWeaponChangePacket val(int val) {
		this.val = val;
		return this;
	}

	public final int val() {
		return this.val;
	}

	public final ClientboundWeaponChangePacket Unnamed0(int Unnamed0) {
		this.Unnamed0 = Unnamed0;
		return this;
	}

	public final int Unnamed0() {
		return this.Unnamed0;
	}

	public final ClientboundWeaponChangePacket val3(long val3) {
		this.val3 = val3;
		return this;
	}

	public final long val3() {
		return this.val3;
	}

	@Override
	public int packetId() {
		return 415;
	}

	@Override
	public final void write(ByteBuf buffer) {
		buffer.writeIntLE(this.val);
		buffer.writeIntLE(this.Unnamed0);
		buffer.writeLongLE(this.val3);
	}
}