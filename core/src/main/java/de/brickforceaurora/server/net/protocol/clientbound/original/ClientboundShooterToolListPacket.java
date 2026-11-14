package de.brickforceaurora.server.net.protocol.clientbound.original;

import de.brickforceaurora.server.net.protocol.IClientboundPacket;
import de.brickforceaurora.server.net.protocol.PacketBuf;
import de.brickforceaurora.server.net.protocol.data.ToolSlot;

public final class ClientboundShooterToolListPacket implements IClientboundPacket {

	private ToolSlot[] slots = new ToolSlot[0];

	public final ClientboundShooterToolListPacket slots(ToolSlot[] slots) {
		this.slots = slots;
		return this;
	}

	public final ToolSlot[] slots() {
		return this.slots;
	}

	@Override
	public int packetId() {
		return 462;
	}

	@Override
	public final void write(PacketBuf buf) {
		buf.writeInt(this.slots.length);
		for (ToolSlot entry : this.slots) {
			buf.writeByte(entry.slot());
			buf.writeLong(entry.item());
		}
	}
}
