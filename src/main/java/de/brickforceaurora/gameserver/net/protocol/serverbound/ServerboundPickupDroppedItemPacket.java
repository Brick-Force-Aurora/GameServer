package de.brickforceaurora.gameserver.net.protocol.serverbound;

import de.brickforceaurora.gameserver.net.protocol.IServerboundPacket;
import io.netty.buffer.ByteBuf;

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
	public final void read(ByteBuf buffer) {
		this.itemSeq = buffer.readIntLE();
	}
}