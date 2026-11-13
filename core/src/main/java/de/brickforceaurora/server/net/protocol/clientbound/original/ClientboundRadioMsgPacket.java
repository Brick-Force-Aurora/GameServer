package de.brickforceaurora.server.net.protocol.clientbound.original;

import de.brickforceaurora.server.net.protocol.IClientboundPacket;
import de.brickforceaurora.server.net.protocol.PacketBuf;

public final class ClientboundRadioMsgPacket implements IClientboundPacket {

	private int clientId;
	private int category;
	private int message;

	public final ClientboundRadioMsgPacket clientId(int clientId) {
		this.clientId = clientId;
		return this;
	}

	public final int clientId() {
		return this.clientId;
	}

	public final ClientboundRadioMsgPacket category(int category) {
		this.category = category;
		return this;
	}

	public final int category() {
		return this.category;
	}

	public final ClientboundRadioMsgPacket message(int message) {
		this.message = message;
		return this;
	}

	public final int message() {
		return this.message;
	}

	@Override
	public int packetId() {
		return 96;
	}

	@Override
	public final void write(PacketBuf buf) {
		buf.writeInt(this.clientId);
		buf.writeInt(this.category);
		buf.writeInt(this.message);
	}
}
