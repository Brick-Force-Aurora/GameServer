package de.brickforceaurora.server.net.protocol.clientbound.original;

import de.brickforceaurora.server.net.protocol.IClientboundPacket;
import de.brickforceaurora.server.net.protocol.PacketBuf;

public final class ClientboundChargePicknwinCoinPacket implements IClientboundPacket {

	private int val;
	private long Unnamed0;
	private String val3;
	private int val4;

	public final ClientboundChargePicknwinCoinPacket val(int val) {
		this.val = val;
		return this;
	}

	public final int val() {
		return this.val;
	}

	public final ClientboundChargePicknwinCoinPacket Unnamed0(long Unnamed0) {
		this.Unnamed0 = Unnamed0;
		return this;
	}

	public final long Unnamed0() {
		return this.Unnamed0;
	}

	public final ClientboundChargePicknwinCoinPacket val3(String val3) {
		this.val3 = val3;
		return this;
	}

	public final String val3() {
		return this.val3;
	}

	public final ClientboundChargePicknwinCoinPacket val4(int val4) {
		this.val4 = val4;
		return this;
	}

	public final int val4() {
		return this.val4;
	}

	@Override
	public int packetId() {
		return 398;
	}

	@Override
	public final void write(PacketBuf buf) {
		buf.writeInt(this.val);
		buf.writeLong(this.Unnamed0);
		buf.writeString(this.val3);
		buf.writeInt(this.val4);
	}
}