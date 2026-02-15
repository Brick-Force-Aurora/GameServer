package de.brickforceaurora.server.net.protocol.serverbound.original;

import de.brickforceaurora.server.net.protocol.IServerboundPacket;
import de.brickforceaurora.server.net.protocol.PacketBuf;

public final class ServerboundPickupDroppedItemPacket implements IServerboundPacket {

	private int itemSeq;

	public final ServerboundPickupDroppedItemPacket itemSeq(int itemSeq) {
		this.itemSeq = itemSeq;
		return this;
	}

	public final int itemSeq() {
		return this.itemSeq;
	}

	@Override
	public int packetId() {
		return 528;
	}

	@Override
	public final void read(PacketBuf buf) {
		this.itemSeq = buf.readInt();
	}
}