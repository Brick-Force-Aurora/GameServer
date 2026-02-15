package de.brickforceaurora.server.net.protocol.serverbound.original;

import de.brickforceaurora.server.net.protocol.IServerboundPacket;
import de.brickforceaurora.server.net.protocol.PacketBuf;

public final class ServerboundRoomPacket implements IServerboundPacket {

	private int no;

	public final ServerboundRoomPacket no(int no) {
		this.no = no;
		return this;
	}

	public final int no() {
		return this.no;
	}

	@Override
	public int packetId() {
		return 469;
	}

	@Override
	public final void read(PacketBuf buf) {
		this.no = buf.readInt();
	}
}