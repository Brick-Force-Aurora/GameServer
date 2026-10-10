package de.brickforceaurora.server.net.protocol.clientbound.original;

import de.brickforceaurora.server.net.protocol.IClientboundPacket;
import de.brickforceaurora.server.net.protocol.PacketBuf;

public final class ClientboundDelRoomPacket implements IClientboundPacket {

	private int roomId;

	public final ClientboundDelRoomPacket roomId(int roomId) {
		this.roomId = roomId;
		return this;
	}

	public final int roomId() {
		return this.roomId;
	}

	@Override
	public int packetId() {
		return 6;
	}

	@Override
	public final void write(PacketBuf buf) {
		buf.writeInt(this.roomId);
	}
}