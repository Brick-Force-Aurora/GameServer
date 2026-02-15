package de.brickforceaurora.server.net.protocol.serverbound.original;

import de.brickforceaurora.server.net.protocol.IServerboundPacket;
import de.brickforceaurora.server.net.protocol.PacketBuf;

public final class ServerboundMorphBrickPacket implements IServerboundPacket {

	private int clientId;
	private int code;

	public final ServerboundMorphBrickPacket clientId(int clientId) {
		this.clientId = clientId;
		return this;
	}

	public final int clientId() {
		return this.clientId;
	}

	public final ServerboundMorphBrickPacket code(int code) {
		if (code > 65535L || code < 0L) {
			throw new IllegalArgumentException(
					"Value " + code + " is out of bounds of allowed number range of 0 - 65535");
		}
		this.code = code;
		return this;
	}

	public final int code() {
		return this.code;
	}

	@Override
	public int packetId() {
		return 33;
	}

	@Override
	public final void read(PacketBuf buf) {
		this.clientId = buf.readInt();
		this.code = buf.readUnsignedShort();
	}
}