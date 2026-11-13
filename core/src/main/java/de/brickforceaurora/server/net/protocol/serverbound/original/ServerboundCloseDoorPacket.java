package de.brickforceaurora.server.net.protocol.serverbound.original;

import de.brickforceaurora.server.net.protocol.IServerboundPacket;
import de.brickforceaurora.server.net.protocol.PacketBuf;

public final class ServerboundCloseDoorPacket implements IServerboundPacket {

	private int door;

	public final ServerboundCloseDoorPacket door(int door) {
		this.door = door;
		return this;
	}

	public final int door() {
		return this.door;
	}

	@Override
	public int packetId() {
		return 448;
	}

	@Override
	public final void read(PacketBuf buf) {
		this.door = buf.readInt();
	}
}
