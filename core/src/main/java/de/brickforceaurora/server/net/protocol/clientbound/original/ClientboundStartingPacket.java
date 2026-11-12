package de.brickforceaurora.server.net.protocol.clientbound.original;

import de.brickforceaurora.server.net.protocol.IClientboundPacket;
import de.brickforceaurora.server.net.protocol.PacketBuf;

public final class ClientboundStartingPacket implements IClientboundPacket {

	private int remain;

	public final ClientboundStartingPacket remain(int remain) {
		this.remain = remain;
		return this;
	}

	public final int remain() {
		return this.remain;
	}

	@Override
	public int packetId() {
		return 390;
	}

	@Override
	public final void write(PacketBuf buf) {
		buf.writeInt(this.remain);
	}
}