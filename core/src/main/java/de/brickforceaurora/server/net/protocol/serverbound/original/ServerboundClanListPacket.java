package de.brickforceaurora.server.net.protocol.serverbound.original;

import de.brickforceaurora.server.net.protocol.IServerboundPacket;
import de.brickforceaurora.server.net.protocol.PacketBuf;

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
	public final void read(PacketBuf buf) {
		this.prev = buf.readInt();
		this.next = buf.readInt();
		this.index = buf.readInt();
		this.keyword = buf.readString();
	}
}