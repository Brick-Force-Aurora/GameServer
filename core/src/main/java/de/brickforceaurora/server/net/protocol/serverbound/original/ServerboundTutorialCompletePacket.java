package de.brickforceaurora.server.net.protocol.serverbound.original;

import de.brickforceaurora.server.net.protocol.IServerboundPacket;
import de.brickforceaurora.server.net.protocol.PacketBuf;

public final class ServerboundTutorialCompletePacket implements IServerboundPacket {

	private int endOf;

	public final ServerboundTutorialCompletePacket endOf(int endOf) {
		if (endOf > 255L || endOf < 0L) {
			throw new IllegalArgumentException(
					"Value " + endOf + " is out of bounds of allowed number range of 0 - 255");
		}
		this.endOf = endOf;
		return this;
	}

	public final int endOf() {
		return this.endOf;
	}

	@Override
	public int packetId() {
		return 170;
	}

	@Override
	public final void read(PacketBuf buf) {
		this.endOf = buf.readUnsignedByte();
	}
}