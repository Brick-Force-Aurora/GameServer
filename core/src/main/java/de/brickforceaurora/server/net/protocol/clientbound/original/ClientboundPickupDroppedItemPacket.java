package de.brickforceaurora.server.net.protocol.clientbound.original;

import de.brickforceaurora.server.net.protocol.IClientboundPacket;
import de.brickforceaurora.server.net.protocol.PacketBuf;

public final class ClientboundPickupDroppedItemPacket implements IClientboundPacket {

	private int clientId;
	private int itemSeq;

	public final ClientboundPickupDroppedItemPacket clientId(int clientId) {
		this.clientId = clientId;
		return this;
	}

	public final int clientId() {
		return this.clientId;
	}

	public final ClientboundPickupDroppedItemPacket itemSeq(int itemSeq) {
		this.itemSeq = itemSeq;
		return this;
	}

	public final int itemSeq() {
		return this.itemSeq;
	}

	@Override
	public int packetId() {
		return 529;
	}

	@Override
	public final void write(PacketBuf buf) {
		buf.writeInt(this.clientId);
		buf.writeInt(this.itemSeq);
	}
}
