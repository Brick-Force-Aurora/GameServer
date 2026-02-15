package de.brickforceaurora.server.net.protocol.serverbound.original;

import de.brickforceaurora.server.net.protocol.IServerboundPacket;
import de.brickforceaurora.server.net.protocol.PacketBuf;

public final class ServerboundClanMatchRecordListPacket implements IServerboundPacket {

	private int prevPage;
	private int nextPage;
	private long index;
	private int clan;

	public final ServerboundClanMatchRecordListPacket prevPage(int prevPage) {
		this.prevPage = prevPage;
		return this;
	}

	public final int prevPage() {
		return this.prevPage;
	}

	public final ServerboundClanMatchRecordListPacket nextPage(int nextPage) {
		this.nextPage = nextPage;
		return this;
	}

	public final int nextPage() {
		return this.nextPage;
	}

	public final ServerboundClanMatchRecordListPacket index(long index) {
		this.index = index;
		return this;
	}

	public final long index() {
		return this.index;
	}

	public final ServerboundClanMatchRecordListPacket clan(int clan) {
		this.clan = clan;
		return this;
	}

	public final int clan() {
		return this.clan;
	}

	@Override
	public int packetId() {
		return 268;
	}

	@Override
	public final void read(PacketBuf buf) {
		this.prevPage = buf.readInt();
		this.nextPage = buf.readInt();
		this.index = buf.readLong();
		this.clan = buf.readInt();
	}
}