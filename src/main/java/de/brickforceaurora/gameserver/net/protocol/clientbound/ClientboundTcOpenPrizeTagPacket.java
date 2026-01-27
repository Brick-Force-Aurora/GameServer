package de.brickforceaurora.gameserver.net.protocol.clientbound;

import de.brickforceaurora.gameserver.net.protocol.IClientboundPacket;
import io.netty.buffer.ByteBuf;

public final class ClientboundTcOpenPrizeTagPacket implements IClientboundPacket {

	private long val;
	private int Unnamed0;
	private int val3;
	private int val4;
	private boolean val5;
	private boolean val6;

	public final ClientboundTcOpenPrizeTagPacket val(long val) {
		this.val = val;
		return this;
	}

	public final long val() {
		return this.val;
	}

	public final ClientboundTcOpenPrizeTagPacket Unnamed0(int Unnamed0) {
		this.Unnamed0 = Unnamed0;
		return this;
	}

	public final int Unnamed0() {
		return this.Unnamed0;
	}

	public final ClientboundTcOpenPrizeTagPacket val3(int val3) {
		this.val3 = val3;
		return this;
	}

	public final int val3() {
		return this.val3;
	}

	public final ClientboundTcOpenPrizeTagPacket val4(int val4) {
		this.val4 = val4;
		return this;
	}

	public final int val4() {
		return this.val4;
	}

	public final ClientboundTcOpenPrizeTagPacket val5(boolean val5) {
		this.val5 = val5;
		return this;
	}

	public final boolean val5() {
		return this.val5;
	}

	public final ClientboundTcOpenPrizeTagPacket val6(boolean val6) {
		this.val6 = val6;
		return this;
	}

	public final boolean val6() {
		return this.val6;
	}

	@Override
	public int packetId() {
		return 376;
	}

	@Override
	public final void write(ByteBuf buffer) {
		buffer.writeLongLE(this.val);
		buffer.writeIntLE(this.Unnamed0);
		buffer.writeIntLE(this.val3);
		buffer.writeIntLE(this.val4);
		buffer.writeBoolean(this.val5);
		buffer.writeBoolean(this.val6);
	}
}