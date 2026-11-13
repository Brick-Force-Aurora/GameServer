package de.brickforceaurora.server.net.protocol.clientbound.original;

import de.brickforceaurora.server.net.protocol.IClientboundPacket;
import de.brickforceaurora.server.net.protocol.PacketBuf;

public final class ClientboundChgDoorStatusPacket implements IClientboundPacket {

	private int door;
	private byte status;

	public final ClientboundChgDoorStatusPacket door(int door) {
		this.door = door;
		return this;
	}

	public final int door() {
		return this.door;
	}

	public final ClientboundChgDoorStatusPacket status(byte status) {
		this.status = status;
		return this;
	}

	public final byte status() {
		return this.status;
	}

	@Override
	public int packetId() {
		return 449;
	}

	@Override
	public final void write(PacketBuf buf) {
		buf.writeInt(this.door);
		buf.writeByte(this.status);
	}
}
