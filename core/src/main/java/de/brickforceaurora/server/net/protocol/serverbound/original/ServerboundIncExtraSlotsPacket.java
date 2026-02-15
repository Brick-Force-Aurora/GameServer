package de.brickforceaurora.server.net.protocol.serverbound.original;

import de.brickforceaurora.server.net.protocol.IServerboundPacket;
import de.brickforceaurora.server.net.protocol.PacketBuf;

public final class ServerboundIncExtraSlotsPacket implements IServerboundPacket {

	private long item;
	private String itemCode;

	public final ServerboundIncExtraSlotsPacket item(long item) {
		this.item = item;
		return this;
	}

	public final long item() {
		return this.item;
	}

	public final ServerboundIncExtraSlotsPacket itemCode(String itemCode) {
		this.itemCode = itemCode;
		return this;
	}

	public final String itemCode() {
		return this.itemCode;
	}

	@Override
	public int packetId() {
		return 407;
	}

	@Override
	public final void read(PacketBuf buf) {
		this.item = buf.readLong();
		this.itemCode = buf.readString();
	}
}