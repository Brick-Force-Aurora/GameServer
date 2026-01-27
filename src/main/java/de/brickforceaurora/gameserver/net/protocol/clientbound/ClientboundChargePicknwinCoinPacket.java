package de.brickforceaurora.gameserver.net.protocol.clientbound;

import de.brickforceaurora.gameserver.net.protocol.IClientboundPacket;
import io.netty.buffer.ByteBuf;
import java.nio.charset.StandardCharsets;

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
	public final void write(ByteBuf buffer) {
		buffer.writeIntLE(this.val);
		buffer.writeLongLE(this.Unnamed0);
		if (this.val3.isEmpty()) {
			buffer.writeIntLE(0);
		} else {
			byte[] bytes = this.val3.getBytes(StandardCharsets.UTF_16LE);
			buffer.writeIntLE(bytes.length);
			buffer.writeBytes(bytes);
		}
		buffer.writeIntLE(this.val4);
	}
}