package de.brickforceaurora.gameserver.net.protocol.clientbound;

import de.brickforceaurora.gameserver.net.protocol.IClientboundPacket;
import io.netty.buffer.ByteBuf;

public final class ClientboundTcUpdateChestPacket implements IClientboundPacket {

	private int val;
	private int val2;
	private int val3;
	private int val4;

	public final ClientboundTcUpdateChestPacket val(int val) {
		this.val = val;
		return this;
	}

	public final int val() {
		return this.val;
	}

	public final ClientboundTcUpdateChestPacket val2(int val2) {
		this.val2 = val2;
		return this;
	}

	public final int val2() {
		return this.val2;
	}

	public final ClientboundTcUpdateChestPacket val3(int val3) {
		this.val3 = val3;
		return this;
	}

	public final int val3() {
		return this.val3;
	}

	public final ClientboundTcUpdateChestPacket val4(int val4) {
		this.val4 = val4;
		return this;
	}

	public final int val4() {
		return this.val4;
	}

	@Override
	public int packetId() {
		return 378;
	}

	@Override
	public final void write(ByteBuf buffer) {
		buffer.writeIntLE(this.val);
		buffer.writeIntLE(this.val2);
		buffer.writeIntLE(this.val3);
		buffer.writeIntLE(this.val4);
	}
}