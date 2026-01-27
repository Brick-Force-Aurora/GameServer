package de.brickforceaurora.gameserver.net.protocol.serverbound;

import de.brickforceaurora.gameserver.net.protocol.IServerboundPacket;
import io.netty.buffer.ByteBuf;
import java.nio.charset.StandardCharsets;

public final class ServerboundAllMapPacket implements IServerboundPacket {

	private int prevPage;
	private int nextPage;
	private int indexer;
	private int modeMask;
	private int flag;
	private String filter;

	public final ServerboundAllMapPacket prevPage(int prevPage) {
		this.prevPage = prevPage;
		return this;
	}

	public final int prevPage() {
		return this.prevPage;
	}

	public final ServerboundAllMapPacket nextPage(int nextPage) {
		this.nextPage = nextPage;
		return this;
	}

	public final int nextPage() {
		return this.nextPage;
	}

	public final ServerboundAllMapPacket indexer(int indexer) {
		this.indexer = indexer;
		return this;
	}

	public final int indexer() {
		return this.indexer;
	}

	public final ServerboundAllMapPacket modeMask(int modeMask) {
		if (modeMask > 32767L || modeMask < 0L) {
			throw new IllegalArgumentException(
					"Value " + modeMask + " is out of bounds of allowed number range of 0 - 32767");
		}
		this.modeMask = modeMask;
		return this;
	}

	public final int modeMask() {
		return this.modeMask;
	}

	public final ServerboundAllMapPacket flag(int flag) {
		this.flag = flag;
		return this;
	}

	public final int flag() {
		return this.flag;
	}

	public final ServerboundAllMapPacket filter(String filter) {
		this.filter = filter;
		return this;
	}

	public final String filter() {
		return this.filter;
	}

	@Override
	public int packetId() {
		return 431;
	}

	@Override
	public final void read(ByteBuf buffer) {
		this.prevPage = buffer.readIntLE();
		this.nextPage = buffer.readIntLE();
		this.indexer = buffer.readIntLE();
		this.modeMask = buffer.readUnsignedShortLE();
		this.flag = buffer.readIntLE();
		{
			int length = buffer.readIntLE();
			if (length == 0) {
				this.filter = "";
			} else {
				byte[] bytes = new byte[length];
				buffer.readBytes(bytes);
				this.filter = new String(bytes, StandardCharsets.UTF_16LE);
			}
		}
	}
}