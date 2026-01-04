package de.brickforceaurora.gameserver.net.protocol.serverbound;

import de.brickforceaurora.gameserver.net.protocol.IServerboundPacket;
import io.netty.buffer.ByteBuf;

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
	public final void read(ByteBuf buffer) {
		this.prevPage = buffer.readIntLE();
		this.nextPage = buffer.readIntLE();
		this.index = buffer.readLongLE();
		this.clan = buffer.readIntLE();
	}
}