package de.brickforceaurora.server.net.protocol.serverbound.original;

import de.brickforceaurora.server.net.protocol.IServerboundPacket;
import de.brickforceaurora.server.net.protocol.PacketBuf;

public final class ServerboundLoadCompletePacket implements IServerboundPacket {

	private int crc;

	public final ServerboundLoadCompletePacket crc(int crc) {
		this.crc = crc;
		return this;
	}

	public final int crc() {
		return this.crc;
	}

	@Override
	public int packetId() {
		return 42;
	}

	@Override
	public final void read(PacketBuf buf) {
		this.crc = buf.readInt();
	}
}