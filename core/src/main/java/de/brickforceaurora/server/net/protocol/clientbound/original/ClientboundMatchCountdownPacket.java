package de.brickforceaurora.server.net.protocol.clientbound.original;

import de.brickforceaurora.server.net.protocol.IClientboundPacket;
import de.brickforceaurora.server.net.protocol.PacketBuf;

public final class ClientboundMatchCountdownPacket implements IClientboundPacket {

	private int count;

	public final ClientboundMatchCountdownPacket count(int count) {
		this.count = count;
		return this;
	}

	public final int count() {
		return this.count;
	}

	@Override
	public int packetId() {
		return 72;
	}

	@Override
	public final void write(PacketBuf buf) {
		buf.writeInt(this.count);
	}
}