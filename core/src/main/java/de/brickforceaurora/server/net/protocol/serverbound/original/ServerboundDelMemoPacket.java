package de.brickforceaurora.server.net.protocol.serverbound.original;

import de.brickforceaurora.server.net.protocol.IServerboundPacket;
import de.brickforceaurora.server.net.protocol.PacketBuf;

public final class ServerboundDelMemoPacket implements IServerboundPacket {

	private long seq;

	public final ServerboundDelMemoPacket seq(long seq) {
		this.seq = seq;
		return this;
	}

	public final long seq() {
		return this.seq;
	}

	@Override
	public int packetId() {
		return 130;
	}

	@Override
	public final void read(PacketBuf buf) {
		this.seq = buf.readLong();
	}
}