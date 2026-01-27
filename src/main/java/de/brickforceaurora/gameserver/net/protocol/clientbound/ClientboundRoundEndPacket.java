package de.brickforceaurora.gameserver.net.protocol.clientbound;

import de.brickforceaurora.gameserver.net.protocol.IClientboundPacket;
import io.netty.buffer.ByteBuf;

public final class ClientboundRoundEndPacket implements IClientboundPacket {

	private int val;
	private byte val2;
	private byte val3;
	private byte val4;

	public final ClientboundRoundEndPacket val(int val) {
		this.val = val;
		return this;
	}

	public final int val() {
		return this.val;
	}

	public final ClientboundRoundEndPacket val2(byte val2) {
		this.val2 = val2;
		return this;
	}

	public final byte val2() {
		return this.val2;
	}

	public final ClientboundRoundEndPacket val3(byte val3) {
		this.val3 = val3;
		return this;
	}

	public final byte val3() {
		return this.val3;
	}

	public final ClientboundRoundEndPacket val4(byte val4) {
		this.val4 = val4;
		return this;
	}

	public final byte val4() {
		return this.val4;
	}

	@Override
	public int packetId() {
		return 205;
	}

	@Override
	public final void write(ByteBuf buffer) {
		buffer.writeIntLE(this.val);
		buffer.writeByte(this.val2);
		buffer.writeByte(this.val3);
		buffer.writeByte(this.val4);
	}
}