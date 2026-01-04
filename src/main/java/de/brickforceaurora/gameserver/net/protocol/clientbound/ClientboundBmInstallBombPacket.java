package de.brickforceaurora.gameserver.net.protocol.clientbound;

import de.brickforceaurora.gameserver.net.protocol.IClientboundPacket;
import io.netty.buffer.ByteBuf;

public final class ClientboundBmInstallBombPacket implements IClientboundPacket {

	private int val;
	private int val2;
	private float val3;
	private float val4;
	private float val5;
	private float val6;
	private float val7;
	private float val8;

	public final ClientboundBmInstallBombPacket val(int val) {
		this.val = val;
		return this;
	}

	public final int val() {
		return this.val;
	}

	public final ClientboundBmInstallBombPacket val2(int val2) {
		this.val2 = val2;
		return this;
	}

	public final int val2() {
		return this.val2;
	}

	public final ClientboundBmInstallBombPacket val3(float val3) {
		this.val3 = val3;
		return this;
	}

	public final float val3() {
		return this.val3;
	}

	public final ClientboundBmInstallBombPacket val4(float val4) {
		this.val4 = val4;
		return this;
	}

	public final float val4() {
		return this.val4;
	}

	public final ClientboundBmInstallBombPacket val5(float val5) {
		this.val5 = val5;
		return this;
	}

	public final float val5() {
		return this.val5;
	}

	public final ClientboundBmInstallBombPacket val6(float val6) {
		this.val6 = val6;
		return this;
	}

	public final float val6() {
		return this.val6;
	}

	public final ClientboundBmInstallBombPacket val7(float val7) {
		this.val7 = val7;
		return this;
	}

	public final float val7() {
		return this.val7;
	}

	public final ClientboundBmInstallBombPacket val8(float val8) {
		this.val8 = val8;
		return this;
	}

	public final float val8() {
		return this.val8;
	}

	@Override
	public int packetId() {
		return 280;
	}

	@Override
	public final void write(ByteBuf buffer) {
		buffer.writeIntLE(this.val);
		buffer.writeIntLE(this.val2);
		buffer.writeFloatLE(this.val3);
		buffer.writeFloatLE(this.val4);
		buffer.writeFloatLE(this.val5);
		buffer.writeFloatLE(this.val6);
		buffer.writeFloatLE(this.val7);
		buffer.writeFloatLE(this.val8);
	}
}