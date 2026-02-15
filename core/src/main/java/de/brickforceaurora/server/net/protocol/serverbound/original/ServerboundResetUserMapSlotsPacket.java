package de.brickforceaurora.server.net.protocol.serverbound.original;

import de.brickforceaurora.server.net.protocol.IServerboundPacket;
import de.brickforceaurora.server.net.protocol.PacketBuf;

public final class ServerboundResetUserMapSlotsPacket implements IServerboundPacket {

	private int slot;
	private long item;
	private String itemCode;

	public final ServerboundResetUserMapSlotsPacket slot(int slot) {
		this.slot = slot;
		return this;
	}

	public final int slot() {
		return this.slot;
	}

	public final ServerboundResetUserMapSlotsPacket item(long item) {
		this.item = item;
		return this;
	}

	public final long item() {
		return this.item;
	}

	public final ServerboundResetUserMapSlotsPacket itemCode(String itemCode) {
		this.itemCode = itemCode;
		return this;
	}

	public final String itemCode() {
		return this.itemCode;
	}

	@Override
	public int packetId() {
		return 405;
	}

	@Override
	public final void read(PacketBuf buf) {
		this.slot = buf.readInt();
		this.item = buf.readLong();
		this.itemCode = buf.readString();
	}
}