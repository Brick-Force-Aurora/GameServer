package de.brickforceaurora.server.net.protocol.serverbound.original;

import de.brickforceaurora.server.net.protocol.IServerboundPacket;
import de.brickforceaurora.server.net.protocol.PacketBuf;

public final class ServerboundSendMemoPacket implements IServerboundPacket {

	private String receiver;
	private String title;
	private String contents;

	public final ServerboundSendMemoPacket receiver(String receiver) {
		this.receiver = receiver;
		return this;
	}

	public final String receiver() {
		return this.receiver;
	}

	public final ServerboundSendMemoPacket title(String title) {
		this.title = title;
		return this;
	}

	public final String title() {
		return this.title;
	}

	public final ServerboundSendMemoPacket contents(String contents) {
		this.contents = contents;
		return this;
	}

	public final String contents() {
		return this.contents;
	}

	@Override
	public int packetId() {
		return 126;
	}

	@Override
	public final void read(PacketBuf buf) {
		this.receiver = buf.readString();
		this.title = buf.readString();
		this.contents = buf.readString();
	}
}