package de.brickforceaurora.server.net.protocol.serverbound.original;

import de.brickforceaurora.server.net.protocol.IServerboundPacket;
import de.brickforceaurora.server.net.protocol.PacketBuf;

public final class ServerboundClanChatPacket implements IServerboundPacket {

	private String text;

	public final ServerboundClanChatPacket text(String text) {
		this.text = text;
		return this;
	}

	public final String text() {
		return this.text;
	}

	@Override
	public int packetId() {
		return 94;
	}

	@Override
	public final void read(PacketBuf buf) {
		this.text = buf.readString();
	}
}