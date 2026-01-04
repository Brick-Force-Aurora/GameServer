package de.brickforceaurora.gameserver.net.protocol.clientbound;

import de.brickforceaurora.gameserver.net.protocol.IClientboundPacket;
import io.netty.buffer.ByteBuf;

public final class ClientboundCtfDropFlagPacket implements IClientboundPacket {

	private int Unnamed0;
	private int Unnamed1;
	private float val3;
	private float val4;
	private float val5;

	public final ClientboundCtfDropFlagPacket Unnamed0(int Unnamed0) {
		this.Unnamed0 = Unnamed0;
		return this;
	}

	public final int Unnamed0() {
		return this.Unnamed0;
	}

	public final ClientboundCtfDropFlagPacket Unnamed1(int Unnamed1) {
		this.Unnamed1 = Unnamed1;
		return this;
	}

	public final int Unnamed1() {
		return this.Unnamed1;
	}

	public final ClientboundCtfDropFlagPacket val3(float val3) {
		this.val3 = val3;
		return this;
	}

	public final float val3() {
		return this.val3;
	}

	public final ClientboundCtfDropFlagPacket val4(float val4) {
		this.val4 = val4;
		return this;
	}

	public final float val4() {
		return this.val4;
	}

	public final ClientboundCtfDropFlagPacket val5(float val5) {
		this.val5 = val5;
		return this;
	}

	public final float val5() {
		return this.val5;
	}

	@Override
	public int packetId() {
		return 290;
	}

	@Override
	public final void write(ByteBuf buffer) {
		buffer.writeIntLE(this.Unnamed0);
		buffer.writeIntLE(this.Unnamed1);
		buffer.writeFloatLE(this.val3);
		buffer.writeFloatLE(this.val4);
		buffer.writeFloatLE(this.val5);
	}
}