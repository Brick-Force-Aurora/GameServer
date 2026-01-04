package de.brickforceaurora.gameserver.net.protocol.serverbound;

import de.brickforceaurora.gameserver.net.protocol.IServerboundPacket;
import io.netty.buffer.ByteBuf;
import java.nio.charset.StandardCharsets;

public final class ServerboundClanListPacket implements IServerboundPacket {

	private int prev;
	private int next;
	private int index;
	private String keyword;

	public final ServerboundClanListPacket prev(int prev) {
		this.prev = prev;
		return this;
	}

	public final int prev() {
		return this.prev;
	}

	public final ServerboundClanListPacket next(int next) {
		this.next = next;
		return this;
	}

	public final int next() {
		return this.next;
	}

	public final ServerboundClanListPacket index(int index) {
		this.index = index;
		return this;
	}

	public final int index() {
		return this.index;
	}

	public final ServerboundClanListPacket keyword(String keyword) {
		this.keyword = keyword;
		return this;
	}

	public final String keyword() {
		return this.keyword;
	}

	@Override
	public int packetId() {
		return 297;
	}

	@Override
	public final void read(ByteBuf buffer) {
		this.prev = buffer.readIntLE();
		this.next = buffer.readIntLE();
		this.index = buffer.readIntLE();
		{
			int length = buffer.readIntLE();
			if (length == 0) {
				this.keyword = "";
			} else {
				byte[] bytes = new byte[length];
				buffer.readBytes(bytes);
				this.keyword = new String(bytes, StandardCharsets.UTF_16LE);
			}
		}
	}
}