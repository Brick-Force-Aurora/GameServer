package de.brickforceaurora.server.net.protocol.clientbound.original;

import de.brickforceaurora.server.net.protocol.IClientboundPacket;
import de.brickforceaurora.server.net.protocol.PacketBuf;

public final class ClientboundCurChannelPacket implements IClientboundPacket {

	private int curChannelId;

	public final ClientboundCurChannelPacket curChannelId(int curChannelId) {
		this.curChannelId = curChannelId;
		return this;
	}

	public final int curChannelId() {
		return this.curChannelId;
	}

	@Override
	public int packetId() {
		return 147;
	}

	@Override
	public final void write(PacketBuf buf) {
		buf.writeInt(this.curChannelId);
	}
}