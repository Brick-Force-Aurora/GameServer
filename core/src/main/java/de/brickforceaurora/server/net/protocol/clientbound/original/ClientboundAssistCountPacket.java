package de.brickforceaurora.server.net.protocol.clientbound.original;

import de.brickforceaurora.server.net.protocol.IClientboundPacket;
import de.brickforceaurora.server.net.protocol.PacketBuf;

public final class ClientboundAssistCountPacket implements IClientboundPacket {

	private int clientId;
	private int assist;
	private int score;

	public final ClientboundAssistCountPacket clientId(int clientId) {
		this.clientId = clientId;
		return this;
	}

	public final int clientId() {
		return this.clientId;
	}

	public final ClientboundAssistCountPacket assist(int assist) {
		this.assist = assist;
		return this;
	}

	public final int assist() {
		return this.assist;
	}

	public final ClientboundAssistCountPacket score(int score) {
		this.score = score;
		return this;
	}

	public final int score() {
		return this.score;
	}

	@Override
	public int packetId() {
		return 185;
	}

	@Override
	public final void write(PacketBuf buf) {
		buf.writeInt(this.clientId);
		buf.writeInt(this.assist);
		buf.writeInt(this.score);
	}
}
