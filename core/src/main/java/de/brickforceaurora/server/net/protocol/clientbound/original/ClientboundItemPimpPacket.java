package de.brickforceaurora.server.net.protocol.clientbound.original;

import de.brickforceaurora.server.net.protocol.IClientboundPacket;
import de.brickforceaurora.server.net.protocol.PacketBuf;

public final class ClientboundItemPimpPacket implements IClientboundPacket {

	private long val;
	private int val2;
	private int val3;

	public final ClientboundItemPimpPacket val(long val) {
		this.val = val;
		return this;
	}

	public final long val() {
		return this.val;
	}

	public final ClientboundItemPimpPacket val2(int val2) {
		this.val2 = val2;
		return this;
	}

	public final int val2() {
		return this.val2;
	}

	public final ClientboundItemPimpPacket val3(int val3) {
		this.val3 = val3;
		return this;
	}

	public final int val3() {
		return this.val3;
	}

	@Override
	public int packetId() {
		return 355;
	}

	@Override
	public final void write(PacketBuf buf) {
		buf.writeLong(this.val);
		buf.writeInt(this.val2);
		buf.writeInt(this.val3);
	}
}