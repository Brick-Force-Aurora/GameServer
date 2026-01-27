package de.brickforceaurora.gameserver.net.protocol.serverbound;

import de.brickforceaurora.gameserver.net.protocol.IServerboundPacket;
import io.netty.buffer.ByteBuf;

public final class ServerboundDownloadMapPacket implements IServerboundPacket {

	private int mapSeq;
	private int buyHow;

	public final ServerboundDownloadMapPacket mapSeq(int mapSeq) {
		this.mapSeq = mapSeq;
		return this;
	}

	public final int mapSeq() {
		return this.mapSeq;
	}

	public final ServerboundDownloadMapPacket buyHow(int buyHow) {
		this.buyHow = buyHow;
		return this;
	}

	public final int buyHow() {
		return this.buyHow;
	}

	@Override
	public int packetId() {
		return 174;
	}

	@Override
	public final void read(ByteBuf buffer) {
		this.mapSeq = buffer.readIntLE();
		this.buyHow = buffer.readIntLE();
	}
}