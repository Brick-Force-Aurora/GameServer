package de.brickforceaurora.gameserver.net.protocol.clientbound;

import de.brickforceaurora.gameserver.net.protocol.IClientboundPacket;
import io.netty.buffer.ByteBuf;

public final class ClientboundBmStatusPacket implements IClientboundPacket {

	private boolean val;
	private int val2;
	private int val3;
	private float val4;
	private float val5;
	private float val6;
	final String UnknownValue0 = "val4";
	final String UnknownValue1 = "val5";
	final String UnknownValue2 = "val6";

	public final ClientboundBmStatusPacket val(boolean val) {
		this.val = val;
		return this;
	}

	public final boolean val() {
		return this.val;
	}

	public final ClientboundBmStatusPacket val2(int val2) {
		this.val2 = val2;
		return this;
	}

	public final int val2() {
		return this.val2;
	}

	public final ClientboundBmStatusPacket val3(int val3) {
		this.val3 = val3;
		return this;
	}

	public final int val3() {
		return this.val3;
	}

	public final ClientboundBmStatusPacket val4(float val4) {
		this.val4 = val4;
		return this;
	}

	public final float val4() {
		return this.val4;
	}

	public final ClientboundBmStatusPacket val5(float val5) {
		this.val5 = val5;
		return this;
	}

	public final float val5() {
		return this.val5;
	}

	public final ClientboundBmStatusPacket val6(float val6) {
		this.val6 = val6;
		return this;
	}

	public final float val6() {
		return this.val6;
	}

	@Override
	public int packetId() {
		return 301;
	}

	@Override
	public final void write(ByteBuf buffer) {
		buffer.writeBoolean(this.val);
		buffer.writeIntLE(this.val2);
		buffer.writeIntLE(this.val3);
		buffer.writeFloatLE(this.val4);
		buffer.writeFloatLE(this.val5);
		buffer.writeFloatLE(this.val6);
	}
}