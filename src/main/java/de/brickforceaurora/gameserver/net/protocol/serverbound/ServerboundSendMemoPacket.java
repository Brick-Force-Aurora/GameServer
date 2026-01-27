package de.brickforceaurora.gameserver.net.protocol.serverbound;

import de.brickforceaurora.gameserver.net.protocol.IServerboundPacket;
import io.netty.buffer.ByteBuf;
import java.nio.charset.StandardCharsets;

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
	public final void read(ByteBuf buffer) {
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