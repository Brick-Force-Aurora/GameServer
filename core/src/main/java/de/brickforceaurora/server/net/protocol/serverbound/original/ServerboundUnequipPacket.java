package de.brickforceaurora.server.net.protocol.serverbound.original;

import de.brickforceaurora.server.net.protocol.IServerboundPacket;
import de.brickforceaurora.server.net.protocol.PacketBuf;

public final class ServerboundUnequipPacket implements IServerboundPacket {

	private long seq;

	public final ServerboundUnequipPacket seq(long seq) {
		this.seq = seq;
		return this;
	}

	public final long seq() {
		return this.seq;
	}

	@Override
	public int packetId() {
		return 37;
	}

	@Override
	public final void read(PacketBuf buf) {
		this.seq = buf.readLong();
	}
}