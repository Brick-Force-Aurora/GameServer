package de.brickforceaurora.server.net.protocol.serverbound.original;

import de.brickforceaurora.server.net.protocol.IServerboundPacket;
import de.brickforceaurora.server.net.protocol.PacketBuf;

public final class ServerboundChgMapIntroPacket implements IServerboundPacket {

	private int clientId;
	private String intro;

	public final ServerboundChgMapIntroPacket clientId(int clientId) {
		this.clientId = clientId;
		return this;
	}

	public final int clientId() {
		return this.clientId;
	}

	public final ServerboundChgMapIntroPacket intro(String intro) {
		this.intro = intro;
		return this;
	}

	public final String intro() {
		return this.intro;
	}

	@Override
	public int packetId() {
		return 441;
	}

	@Override
	public final void read(PacketBuf buf) {
		this.clientId = buf.readInt();
		this.intro = buf.readString();
	}
}