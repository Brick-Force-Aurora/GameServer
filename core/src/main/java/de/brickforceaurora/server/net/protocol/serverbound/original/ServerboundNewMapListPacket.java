package de.brickforceaurora.server.net.protocol.serverbound.original;

import de.brickforceaurora.server.net.protocol.IServerboundPacket;
import de.brickforceaurora.server.net.protocol.PacketBuf;

public final class ServerboundNewMapListPacket implements IServerboundPacket {

	private int prevPage;
	private int nextPage;
	private int indexer;
	private int modeMask;
	private byte flag;
	private String filter;

	public final ServerboundNewMapListPacket prevPage(int prevPage) {
		this.prevPage = prevPage;
		return this;
	}

	public final int prevPage() {
		return this.prevPage;
	}

	public final ServerboundNewMapListPacket nextPage(int nextPage) {
		this.nextPage = nextPage;
		return this;
	}

	public final int nextPage() {
		return this.nextPage;
	}

	public final ServerboundNewMapListPacket indexer(int indexer) {
		this.indexer = indexer;
		return this;
	}

	public final int indexer() {
		return this.indexer;
	}

	public final ServerboundNewMapListPacket modeMask(int modeMask) {
		if (modeMask > 255L || modeMask < 0L) {
			throw new IllegalArgumentException(
					"Value " + modeMask + " is out of bounds of allowed number range of 0 - 255");
		}
		this.modeMask = modeMask;
		return this;
	}

	public final int modeMask() {
		return this.modeMask;
	}

	public final ServerboundNewMapListPacket flag(byte flag) {
		this.flag = flag;
		return this;
	}

	public final byte flag() {
		return this.flag;
	}

	public final ServerboundNewMapListPacket filter(String filter) {
		this.filter = filter;
		return this;
	}

	public final String filter() {
		return this.filter;
	}

	@Override
	public int packetId() {
		return 56;
	}

	@Override
	public final void read(PacketBuf buf) {
		this.prevPage = buf.readInt();
		this.nextPage = buf.readInt();
		this.indexer = buf.readInt();
		this.modeMask = buf.readUnsignedByte();
		this.flag = buf.readByte();
		this.filter = buf.readString();
	}
}