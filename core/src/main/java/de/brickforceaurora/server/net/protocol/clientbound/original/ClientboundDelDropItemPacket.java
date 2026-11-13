package de.brickforceaurora.server.net.protocol.clientbound.original;

import de.brickforceaurora.server.net.protocol.IClientboundPacket;
import de.brickforceaurora.server.net.protocol.PacketBuf;

public final class ClientboundDelDropItemPacket implements IClientboundPacket {

	private int itemSeq;

	public final ClientboundDelDropItemPacket itemSeq(int itemSeq) {
		this.itemSeq = itemSeq;
		return this;
	}

	public final int itemSeq() {
		return this.itemSeq;
	}

	@Override
	public int packetId() {
		return 530;
	}

	@Override
	public final void write(PacketBuf buf) {
		buf.writeInt(this.itemSeq);
	}
}
