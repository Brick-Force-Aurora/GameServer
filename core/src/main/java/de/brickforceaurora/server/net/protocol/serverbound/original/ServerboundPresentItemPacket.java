package de.brickforceaurora.server.net.protocol.serverbound.original;

import de.brickforceaurora.server.net.protocol.IServerboundPacket;
import de.brickforceaurora.server.net.protocol.PacketBuf;

public final class ServerboundPresentItemPacket implements IServerboundPacket {

	private String code;
	private int buyHow;
	private int option;
	private String receiver;
	private String title;
	private String contents;

	public final ServerboundPresentItemPacket code(String code) {
		this.code = code;
		return this;
	}

	public final String code() {
		return this.code;
	}

	public final ServerboundPresentItemPacket buyHow(int buyHow) {
		this.buyHow = buyHow;
		return this;
	}

	public final int buyHow() {
		return this.buyHow;
	}

	public final ServerboundPresentItemPacket option(int option) {
		this.option = option;
		return this;
	}

	public final int option() {
		return this.option;
	}

	public final ServerboundPresentItemPacket receiver(String receiver) {
		this.receiver = receiver;
		return this;
	}

	public final String receiver() {
		return this.receiver;
	}

	public final ServerboundPresentItemPacket title(String title) {
		this.title = title;
		return this;
	}

	public final String title() {
		return this.title;
	}

	public final ServerboundPresentItemPacket contents(String contents) {
		this.contents = contents;
		return this;
	}

	public final String contents() {
		return this.contents;
	}

	@Override
	public int packetId() {
		return 128;
	}

	@Override
	public final void read(PacketBuf buf) {
		this.code = buf.readString();
		this.buyHow = buf.readInt();
		this.option = buf.readInt();
		this.receiver = buf.readString();
		this.title = buf.readString();
		this.contents = buf.readString();
	}
}