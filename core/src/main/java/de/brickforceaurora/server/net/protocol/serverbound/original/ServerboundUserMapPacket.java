package de.brickforceaurora.server.net.protocol.serverbound.original;

import de.brickforceaurora.server.net.protocol.IServerboundPacket;
import de.brickforceaurora.server.net.protocol.PacketBuf;

public final class ServerboundUserMapPacket implements IServerboundPacket {

	private int page;

	public final ServerboundUserMapPacket page(int page) {
		this.page = page;
		return this;
	}

	public final int page() {
		return this.page;
	}

	@Override
	public int packetId() {
		return 429;
	}

	@Override
	public final void read(PacketBuf buf) {
		this.page = buf.readInt();
	}
}