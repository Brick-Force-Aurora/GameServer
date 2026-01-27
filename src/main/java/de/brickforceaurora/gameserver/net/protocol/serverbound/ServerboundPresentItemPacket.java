package de.brickforceaurora.gameserver.net.protocol.serverbound;

import de.brickforceaurora.gameserver.net.protocol.IServerboundPacket;
import io.netty.buffer.ByteBuf;
import java.nio.charset.StandardCharsets;

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
	public final void read(ByteBuf buffer) {
		{
			int length = buffer.readIntLE();
			if (length == 0) {
				this.code = "";
			} else {
				byte[] bytes = new byte[length];
				buffer.readBytes(bytes);
				this.code = new String(bytes, StandardCharsets.UTF_16LE);
			}
		}
		this.buyHow = buffer.readIntLE();
		this.option = buffer.readIntLE();
		{
			int length = buffer.readIntLE();
			if (length == 0) {
				this.receiver = "";
			} else {
				byte[] bytes = new byte[length];
				buffer.readBytes(bytes);
				this.receiver = new String(bytes, StandardCharsets.UTF_16LE);
			}
		}
		{
			int length = buffer.readIntLE();
			if (length == 0) {
				this.title = "";
			} else {
				byte[] bytes = new byte[length];
				buffer.readBytes(bytes);
				this.title = new String(bytes, StandardCharsets.UTF_16LE);
			}
		}
		{
			int length = buffer.readIntLE();
			if (length == 0) {
				this.contents = "";
			} else {
				byte[] bytes = new byte[length];
				buffer.readBytes(bytes);
				this.contents = new String(bytes, StandardCharsets.UTF_16LE);
			}
		}
	}
}