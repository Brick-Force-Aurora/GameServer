package de.brickforceaurora.server.net.protocol.clientbound.original;

import de.brickforceaurora.server.net.protocol.IClientboundPacket;
import de.brickforceaurora.server.net.protocol.PacketBuf;

public final class ClientboundWeaponSlotPacket implements IClientboundPacket {

	private int slot;
	private long item;

	public final ClientboundWeaponSlotPacket slot(int slot) {
		this.slot = slot;
		return this;
	}

	public final int slot() {
		return this.slot;
	}

	public final ClientboundWeaponSlotPacket item(long item) {
		this.item = item;
		return this;
	}

	public final long item() {
		return this.item;
	}

	@Override
	public int packetId() {
		return 418;
	}

	@Override
	public final void write(PacketBuf buf) {
		buf.writeInt(this.slot);
		buf.writeLong(this.item);
	}
}