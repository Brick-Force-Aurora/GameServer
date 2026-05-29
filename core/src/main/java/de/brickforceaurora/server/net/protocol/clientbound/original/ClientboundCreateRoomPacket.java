package de.brickforceaurora.server.net.protocol.clientbound.original;

import de.brickforceaurora.server.net.protocol.IClientboundPacket;
import de.brickforceaurora.server.net.protocol.PacketBuf;
import de.brickforceaurora.server.net.protocol.data.api.IRoomInfo;

public final class ClientboundCreateRoomPacket implements IClientboundPacket {

	private IRoomInfo roomInfo;

	public final ClientboundCreateRoomPacket roomInfo(IRoomInfo roomInfo) {
		this.roomInfo = roomInfo;
		return this;
	}

	public final IRoomInfo roomInfo() {
		return this.roomInfo;
	}

	@Override
	public int packetId() {
		return 8;
	}

	@Override
	public final void write(PacketBuf buf) {
		buf.writeInt(this.roomInfo.type().id());
		buf.writeInt(this.roomInfo.id()); // TODO: -1 if failure
		buf.writeString(this.roomInfo.title());
	}
}