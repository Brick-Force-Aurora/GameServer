package de.brickforceaurora.server.net.protocol.serverbound.original;

import de.brickforceaurora.server.net.protocol.IServerboundPacket;
import de.brickforceaurora.server.net.protocol.PacketBuf;

public final class ServerboundWhisperPacket implements IServerboundPacket {

	private String listener;
	private String text;

	public final ServerboundWhisperPacket listener(String listener) {
		this.listener = listener;
		return this;
	}

	public final String listener() {
		return this.listener;
	}

	public final ServerboundWhisperPacket text(String text) {
		this.text = text;
		return this;
	}

	public final String text() {
		return this.text;
	}

	@Override
	public int packetId() {
		return 26;
	}

	@Override
	public final void read(PacketBuf buf) {
		this.listener = buf.readString();
		this.text = buf.readString();
	}
}