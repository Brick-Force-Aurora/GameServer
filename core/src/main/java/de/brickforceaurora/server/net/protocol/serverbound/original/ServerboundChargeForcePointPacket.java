package de.brickforceaurora.server.net.protocol.serverbound.original;

import de.brickforceaurora.server.net.protocol.IServerboundPacket;
import de.brickforceaurora.server.net.protocol.PacketBuf;

public final class ServerboundChargeForcePointPacket implements IServerboundPacket {

	private long item;
	private String code;

	public final ServerboundChargeForcePointPacket item(long item) {
		this.item = item;
		return this;
	}

	public final long item() {
		return this.item;
	}

	public final ServerboundChargeForcePointPacket code(String code) {
		this.code = code;
		return this;
	}

	public final String code() {
		return this.code;
	}

	@Override
	public int packetId() {
		return 471;
	}

	@Override
	public final void read(PacketBuf buf) {
		this.item = buf.readLong();
		this.code = buf.readString();
	}
}