package de.brickforceaurora.server.net.protocol.serverbound.original;

import de.brickforceaurora.server.net.protocol.IServerboundPacket;
import de.brickforceaurora.server.net.protocol.PacketBuf;

public final class ServerboundAccuseMapPacket implements IServerboundPacket {

	private int reason;
	private int clientId;
	private String contents;

	public final ServerboundAccuseMapPacket reason(int reason) {
		this.reason = reason;
		return this;
	}

	public final int reason() {
		return this.reason;
	}

	public final ServerboundAccuseMapPacket clientId(int clientId) {
		this.clientId = clientId;
		return this;
	}

	public final int clientId() {
		return this.clientId;
	}

	public final ServerboundAccuseMapPacket contents(String contents) {
		this.contents = contents;
		return this;
	}

	public final String contents() {
		return this.contents;
	}

	@Override
	public int packetId() {
		return 512;
	}

	@Override
	public final void read(PacketBuf buf) {
		this.reason = buf.readInt();
		this.clientId = buf.readInt();
		this.contents = buf.readString();
	}
}