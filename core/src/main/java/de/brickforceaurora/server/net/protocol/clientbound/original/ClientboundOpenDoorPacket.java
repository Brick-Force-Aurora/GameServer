package de.brickforceaurora.server.net.protocol.clientbound.original;

import de.brickforceaurora.server.net.protocol.IClientboundPacket;
import de.brickforceaurora.server.net.protocol.PacketBuf;

public final class ClientboundOpenDoorPacket implements IClientboundPacket {

	private int door;

	public final ClientboundOpenDoorPacket door(int door) {
		this.door = door;
		return this;
	}

	public final int door() {
		return this.door;
	}

	@Override
	public int packetId() {
		return 450;
	}

	@Override
	public final void write(PacketBuf buf) {
		buf.writeInt(this.door);
	}
}
