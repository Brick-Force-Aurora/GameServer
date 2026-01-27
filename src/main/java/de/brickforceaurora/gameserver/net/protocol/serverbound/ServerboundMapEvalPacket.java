package de.brickforceaurora.gameserver.net.protocol.serverbound;

import de.brickforceaurora.gameserver.net.protocol.IServerboundPacket;
import io.netty.buffer.ByteBuf;
import java.nio.charset.StandardCharsets;

public final class ServerboundMapEvalPacket implements IServerboundPacket {

	private int map;
	private int likesOrDislikes;
	private String comment;

	public final ServerboundMapEvalPacket map(int map) {
		this.map = map;
		return this;
	}

	public final int map() {
		return this.map;
	}

	public final ServerboundMapEvalPacket likesOrDislikes(int likesOrDislikes) {
		if (likesOrDislikes > 255L || likesOrDislikes < 0L) {
			throw new IllegalArgumentException(
					"Value " + likesOrDislikes + " is out of bounds of allowed number range of 0 - 255");
		}
		this.likesOrDislikes = likesOrDislikes;
		return this;
	}

	public final int likesOrDislikes() {
		return this.likesOrDislikes;
	}

	public final ServerboundMapEvalPacket comment(String comment) {
		this.comment = comment;
		return this;
	}

	public final String comment() {
		return this.comment;
	}

	@Override
	public int packetId() {
		return 423;
	}

	@Override
	public final void read(ByteBuf buffer) {
		this.map = buffer.readIntLE();
		this.likesOrDislikes = buffer.readUnsignedByte();
		{
			int length = buffer.readIntLE();
			if (length == 0) {
				this.comment = "";
			} else {
				byte[] bytes = new byte[length];
				buffer.readBytes(bytes);
				this.comment = new String(bytes, StandardCharsets.UTF_16LE);
			}
		}
	}
}