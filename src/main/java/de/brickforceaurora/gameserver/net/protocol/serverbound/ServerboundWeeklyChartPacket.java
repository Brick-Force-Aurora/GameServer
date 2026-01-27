package de.brickforceaurora.gameserver.net.protocol.serverbound;

import de.brickforceaurora.gameserver.net.protocol.IServerboundPacket;
import io.netty.buffer.ByteBuf;

public final class ServerboundWeeklyChartPacket implements IServerboundPacket {

	private int prevPage;
	private int nextPage;
	private int indexer;

	public final ServerboundWeeklyChartPacket prevPage(int prevPage) {
		this.prevPage = prevPage;
		return this;
	}

	public final int prevPage() {
		return this.prevPage;
	}

	public final ServerboundWeeklyChartPacket nextPage(int nextPage) {
		this.nextPage = nextPage;
		return this;
	}

	public final int nextPage() {
		return this.nextPage;
	}

	public final ServerboundWeeklyChartPacket indexer(int indexer) {
		this.indexer = indexer;
		return this;
	}

	public final int indexer() {
		return this.indexer;
	}

	@Override
	public int packetId() {
		return 433;
	}

	@Override
	public final void read(ByteBuf buffer) {
		this.prevPage = buffer.readIntLE();
		this.nextPage = buffer.readIntLE();
		this.indexer = buffer.readIntLE();
	}
}