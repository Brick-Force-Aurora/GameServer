package de.brickforceaurora.server.net.protocol.clientbound.original;

import de.brickforceaurora.server.net.protocol.IClientboundPacket;
import de.brickforceaurora.server.net.protocol.PacketBuf;
import de.brickforceaurora.server.net.protocol.data.api.IRoomInfo;

public final class ClientboundRoomListPacket implements IClientboundPacket {

	private IRoomInfo[] rooms;

	public final ClientboundRoomListPacket rooms(IRoomInfo[] rooms) {
		this.rooms = rooms;
		return this;
	}

	public final IRoomInfo[] rooms() {
		return this.rooms;
	}

	@Override
	public int packetId() {
		return 468;
	}

	@Override
	public final void write(PacketBuf buf) {
	    buf.writeArray(rooms, IRoomInfo::toBuffer);
	}
}