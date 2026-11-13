package de.brickforceaurora.server.net.protocol.clientbound.original;

import de.brickforceaurora.server.net.protocol.IClientboundPacket;
import de.brickforceaurora.server.net.protocol.PacketBuf;

public final class ClientboundGetTrainPacket implements IClientboundPacket {

	private int shooter;
	private int train;

	public final ClientboundGetTrainPacket shooter(int shooter) {
		this.shooter = shooter;
		return this;
	}

	public final int shooter() {
		return this.shooter;
	}

	public final ClientboundGetTrainPacket train(int train) {
		this.train = train;
		return this;
	}

	public final int train() {
		return this.train;
	}

	@Override
	public int packetId() {
		return 552;
	}

	@Override
	public final void write(PacketBuf buf) {
		buf.writeInt(this.shooter);
		buf.writeInt(this.train);
	}
}
