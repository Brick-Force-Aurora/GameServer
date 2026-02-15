package de.brickforceaurora.server.net.protocol.clientbound.original;

import de.brickforceaurora.server.net.protocol.IClientboundPacket;
import de.brickforceaurora.server.net.protocol.PacketBuf;

public final class ClientboundSeedPacket implements IClientboundPacket {

	private int seed;

	public final ClientboundSeedPacket seed(int seed) {
		if (seed > 255L || seed < 0L) {
			throw new IllegalArgumentException("Value " + seed + " is out of bounds of allowed number range of 0 - 255");
		}
		this.seed = seed;
		return this;
	}

	public final int seed() {
		return this.seed;
	}

	@Override
	public int packetId() {
		return 84;
	}

	@Override
	public final void write(PacketBuf buf) {
		buf.writeByte(this.seed);
	}
}