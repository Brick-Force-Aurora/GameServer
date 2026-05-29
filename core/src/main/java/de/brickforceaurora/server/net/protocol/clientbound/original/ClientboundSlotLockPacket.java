package de.brickforceaurora.server.net.protocol.clientbound.original;

import de.brickforceaurora.server.net.protocol.IClientboundPacket;
import de.brickforceaurora.server.net.protocol.PacketBuf;

public final class ClientboundSlotLockPacket implements IClientboundPacket {

	private byte index;
	private boolean slotLocked;

	public final ClientboundSlotLockPacket index(byte index) {
		this.index = index;
		return this;
	}

	public final byte index() {
		return this.index;
	}

	public final ClientboundSlotLockPacket slotLocked(boolean slotLocked) {
		this.slotLocked = slotLocked;
		return this;
	}

	public final boolean slotLocked() {
		return this.slotLocked;
	}

	@Override
	public int packetId() {
		return 86;
	}

	@Override
	public final void write(PacketBuf buf) {
		buf.writeByte(this.index);
		buf.writeBoolean(this.slotLocked); //byte?
	}
}