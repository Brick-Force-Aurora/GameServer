package de.brickforceaurora.server.net.protocol.clientbound.original;

import de.brickforceaurora.server.net.protocol.IClientboundPacket;
import de.brickforceaurora.server.net.protocol.PacketBuf;

public final class ClientboundYouAreMutePacket implements IClientboundPacket {

	private long val;

	public final ClientboundYouAreMutePacket val(long val) {
		if (val > 4294967295L || val < 0L) {
			throw new IllegalArgumentException(
					"Value " + val + " is out of bounds of allowed number range of 0 - 4294967295");
		}
		this.val = val;
		return this;
	}

	public final long val() {
		return this.val;
	}

	@Override
	public int packetId() {
		return 457;
	}

	@Override
	public final void write(PacketBuf buf) {
		buf.writeInt(this.val);
	}
}