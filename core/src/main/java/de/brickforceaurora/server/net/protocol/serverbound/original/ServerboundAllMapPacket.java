package de.brickforceaurora.server.net.protocol.serverbound.original;

import de.brickforceaurora.server.net.protocol.IServerboundPacket;
import de.brickforceaurora.server.net.protocol.PacketBuf;

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
		if (modeMask > 65535L || modeMask < 0L) {
			throw new IllegalArgumentException(
					"Value " + modeMask + " is out of bounds of allowed number range of 0 - 65535");
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
	public final void read(PacketBuf buf) {
		this.prevPage = buf.readInt();
		this.nextPage = buf.readInt();
		this.indexer = buf.readInt();
		this.modeMask = buf.readUnsignedShort();
		this.flag = buf.readInt();
		this.filter = buf.readString();
	}
}