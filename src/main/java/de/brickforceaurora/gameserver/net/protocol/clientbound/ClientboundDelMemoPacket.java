package de.brickforceaurora.gameserver.net.protocol.clientbound;

import de.brickforceaurora.gameserver.net.protocol.IClientboundPacket;
import io.netty.buffer.ByteBuf;

public final class ClientboundDelMemoPacket implements IClientboundPacket {

	private long val;

	public final ClientboundDelMemoPacket val(long val) {
		this.val = val;
		return this;
	}

	public final long val() {
		return this.val;
	}

	@Override
	public int packetId() {
		return 131;
	}

	@Override
	public final void write(ByteBuf buffer) {
		buffer.writeLongLE(this.val);
	}
}