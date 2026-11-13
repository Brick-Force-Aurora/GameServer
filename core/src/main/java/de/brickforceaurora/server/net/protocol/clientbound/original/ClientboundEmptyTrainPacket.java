package de.brickforceaurora.server.net.protocol.clientbound.original;

import de.brickforceaurora.server.net.protocol.IClientboundPacket;
import de.brickforceaurora.server.net.protocol.PacketBuf;

public final class ClientboundEmptyTrainPacket implements IClientboundPacket {

	private int train;

	public final ClientboundEmptyTrainPacket train(int train) {
		this.train = train;
		return this;
	}

	public final int train() {
		return this.train;
	}

	@Override
	public int packetId() {
		return 554;
	}

	@Override
	public final void write(PacketBuf buf) {
		buf.writeInt(this.train);
	}
}
