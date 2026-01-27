package de.brickforceaurora.gameserver.net.protocol.clientbound;

import de.brickforceaurora.gameserver.net.protocol.IClientboundPacket;
import io.netty.buffer.ByteBuf;

public final class ClientboundMissionRewardPacket implements IClientboundPacket {

	private int val;
	private byte val2;
	private int val3;
	private int val4;

	public final ClientboundMissionRewardPacket val(int val) {
		this.val = val;
		return this;
	}

	public final int val() {
		return this.val;
	}

	public final ClientboundMissionRewardPacket val2(byte val2) {
		this.val2 = val2;
		return this;
	}

	public final byte val2() {
		return this.val2;
	}

	public final ClientboundMissionRewardPacket val3(int val3) {
		this.val3 = val3;
		return this;
	}

	public final int val3() {
		return this.val3;
	}

	public final ClientboundMissionRewardPacket val4(int val4) {
		this.val4 = val4;
		return this;
	}

	public final int val4() {
		return this.val4;
	}

	@Override
	public int packetId() {
		return 490;
	}

	@Override
	public final void write(ByteBuf buffer) {
		buffer.writeIntLE(this.val);
		buffer.writeByte(this.val2);
		buffer.writeIntLE(this.val3);
		buffer.writeIntLE(this.val4);
	}
}