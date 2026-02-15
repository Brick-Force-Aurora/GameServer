package de.brickforceaurora.server.net.protocol.clientbound.original;

import de.brickforceaurora.server.net.protocol.IClientboundPacket;
import de.brickforceaurora.server.net.protocol.PacketBuf;

public final class ClientboundLevelupRewardPacket implements IClientboundPacket {

	private int val;
	private int val2;
	private String val3;
	private int val4;
	private int val5;

	public final ClientboundLevelupRewardPacket val(int val) {
		this.val = val;
		return this;
	}

	public final int val() {
		return this.val;
	}

	public final ClientboundLevelupRewardPacket val2(int val2) {
		this.val2 = val2;
		return this;
	}

	public final int val2() {
		return this.val2;
	}

	public final ClientboundLevelupRewardPacket val3(String val3) {
		this.val3 = val3;
		return this;
	}

	public final String val3() {
		return this.val3;
	}

	public final ClientboundLevelupRewardPacket val4(int val4) {
		this.val4 = val4;
		return this;
	}

	public final int val4() {
		return this.val4;
	}

	public final ClientboundLevelupRewardPacket val5(int val5) {
		if (val5 > 255L || val5 < 0L) {
			throw new IllegalArgumentException(
					"Value " + val5 + " is out of bounds of allowed number range of 0 - 255");
		}
		this.val5 = val5;
		return this;
	}

	public final int val5() {
		return this.val5;
	}

	@Override
	public int packetId() {
		return 489;
	}

	@Override
	public final void write(PacketBuf buf) {
		buf.writeInt(this.val);
		buf.writeInt(this.val2);
		buf.writeString(this.val3);
		buf.writeInt(this.val4);
		buf.writeByte(this.val5);
	}
}