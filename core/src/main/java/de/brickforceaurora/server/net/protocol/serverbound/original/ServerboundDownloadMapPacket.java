package de.brickforceaurora.server.net.protocol.serverbound.original;

import de.brickforceaurora.server.net.protocol.IServerboundPacket;
import de.brickforceaurora.server.net.protocol.PacketBuf;

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
	public final void read(PacketBuf buf) {
		this.mapSeq = buf.readInt();
		this.buyHow = buf.readInt();
	}
}