package de.brickforceaurora.gameserver.net.protocol.clientbound;

import de.brickforceaurora.gameserver.net.protocol.IClientboundPacket;
import io.netty.buffer.ByteBuf;

public final class ClientboundCustomGameConfigPacket implements IClientboundPacket {

	private boolean val;
	private int val2;
	private boolean val3;
	private int val4;
	private int val5;
	private int val6;

	public final ClientboundCustomGameConfigPacket val(boolean val) {
		this.val = val;
		return this;
	}

	public final boolean val() {
		return this.val;
	}

	public final ClientboundCustomGameConfigPacket val2(int val2) {
		this.val2 = val2;
		return this;
	}

	public final int val2() {
		return this.val2;
	}

	public final ClientboundCustomGameConfigPacket val3(boolean val3) {
		this.val3 = val3;
		return this;
	}

	public final boolean val3() {
		return this.val3;
	}

	public final ClientboundCustomGameConfigPacket val4(int val4) {
		this.val4 = val4;
		return this;
	}

	public final int val4() {
		return this.val4;
	}

	public final ClientboundCustomGameConfigPacket val5(int val5) {
		this.val5 = val5;
		return this;
	}

	public final int val5() {
		return this.val5;
	}

	public final ClientboundCustomGameConfigPacket val6(int val6) {
		this.val6 = val6;
		return this;
	}

	public final int val6() {
		return this.val6;
	}

	@Override
	public int packetId() {
		return 500;
	}

	@Override
	public final void write(ByteBuf buffer) {
		buffer.writeBoolean(this.val);
		buffer.writeIntLE(this.val2);
		buffer.writeBoolean(this.val3);
		buffer.writeIntLE(this.val4);
		buffer.writeIntLE(this.val5);
		buffer.writeIntLE(this.val6);
	}
}