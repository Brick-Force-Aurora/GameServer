package de.brickforceaurora.server.net.protocol.serverbound.original;

import de.brickforceaurora.server.net.protocol.IServerboundPacket;
import de.brickforceaurora.server.net.protocol.PacketBuf;

public final class ServerboundMapDetailPacket implements IServerboundPacket {

	private int mapSeq;

	public final ServerboundMapDetailPacket mapSeq(int mapSeq) {
		this.mapSeq = mapSeq;
		return this;
	}

	public final int mapSeq() {
		return this.mapSeq;
	}

	@Override
	public int packetId() {
		return 437;
	}

	@Override
	public final void read(PacketBuf buf) {
		this.mapSeq = buf.readInt();
	}
}