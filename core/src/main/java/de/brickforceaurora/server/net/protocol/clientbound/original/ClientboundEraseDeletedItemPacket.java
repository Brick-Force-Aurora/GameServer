package de.brickforceaurora.server.net.protocol.clientbound.original;

import de.brickforceaurora.server.net.protocol.IClientboundPacket;
import de.brickforceaurora.server.net.protocol.PacketBuf;

public final class ClientboundEraseDeletedItemPacket implements IClientboundPacket {

	private long val;

	public final ClientboundEraseDeletedItemPacket val(long val) {
		this.val = val;
		return this;
	}

	public final long val() {
		return this.val;
	}

	@Override
	public int packetId() {
		return 312;
	}

	@Override
	public final void write(PacketBuf buf) {
		buf.writeLong(this.val);
	}
}