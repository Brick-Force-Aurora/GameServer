package de.brickforceaurora.server.net.protocol.clientbound.original;

import de.brickforceaurora.server.net.protocol.IClientboundPacket;
import de.brickforceaurora.server.net.protocol.PacketBuf;

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
	public final void write(PacketBuf buf) {
		buf.writeLong(this.val);
	}
}