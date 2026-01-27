package de.brickforceaurora.gameserver.net.protocol.clientbound;

import de.brickforceaurora.gameserver.net.protocol.IClientboundPacket;
import io.netty.buffer.ByteBuf;

public final class ClientboundShooterToolListPacket implements IClientboundPacket {

	private int val;
	private byte val2;
	private long val3;

	public final ClientboundShooterToolListPacket val(int val) {
		this.val = val;
		return this;
	}

	public final int val() {
		return this.val;
	}

	public final ClientboundShooterToolListPacket val2(byte val2) {
		this.val2 = val2;
		return this;
	}

	public final byte val2() {
		return this.val2;
	}

	public final ClientboundShooterToolListPacket val3(long val3) {
		this.val3 = val3;
		return this;
	}

	public final long val3() {
		return this.val3;
	}

	@Override
	public int packetId() {
		return 462;
	}

	@Override
	public final void write(ByteBuf buffer) {
		buffer.writeIntLE(this.val);
		buffer.writeByte(this.val2);
		buffer.writeLongLE(this.val3);
	}
}