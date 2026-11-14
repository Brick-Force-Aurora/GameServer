package de.brickforceaurora.server.net.protocol.clientbound.original;

import de.brickforceaurora.server.net.protocol.IClientboundPacket;
import de.brickforceaurora.server.net.protocol.PacketBuf;
import de.brickforceaurora.server.net.protocol.data.ToolSlot;

public final class ClientboundWeaponSlotListPacket implements IClientboundPacket {

	private ToolSlot[] slots = new ToolSlot[0];

	public final ClientboundWeaponSlotListPacket slots(ToolSlot[] slots) {
		this.slots = slots;
		return this;
	}

	public final ToolSlot[] slots() {
		return this.slots;
	}

	@Override
	public int packetId() {
		return 463;
	}

	@Override
	public final void write(PacketBuf buf) {
		buf.writeInt(this.slots.length);
		for (ToolSlot entry : this.slots) {
			buf.writeInt(entry.slot());
			buf.writeLong(entry.item());
		}
	}
}
