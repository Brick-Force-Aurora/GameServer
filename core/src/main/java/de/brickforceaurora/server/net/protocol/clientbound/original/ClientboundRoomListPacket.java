package de.brickforceaurora.server.net.protocol.clientbound.original;

import de.brickforceaurora.server.net.protocol.IClientboundPacket;
import de.brickforceaurora.server.net.protocol.PacketBuf;
import de.brickforceaurora.server.net.protocol.data.RoomInfo;

public final class ClientboundRoomListPacket implements IClientboundPacket {

	private RoomInfo[] rooms;

	public final ClientboundRoomListPacket rooms(RoomInfo[] rooms) {
		this.rooms = rooms;
		return this;
	}

	public final RoomInfo[] rooms() {
		return this.rooms;
	}

	@Override
	public int packetId() {
		return 468;
	}

	@Override
	public final void write(PacketBuf buf) {
		buf.writeInt(this.rooms.length);
		for (RoomInfo roomInfo : this.rooms){
			buf.writeInt(roomInfo.id());
			buf.writeInt(roomInfo.type().id());
			buf.writeString(roomInfo.title());
			buf.writeBoolean(roomInfo.locked());
			buf.writeInt(roomInfo.status().id());
			buf.writeInt(roomInfo.currentPlayerCount());
			buf.writeInt(roomInfo.maxPlayerCount());
			buf.writeInt(roomInfo.mapId());
			buf.writeString(roomInfo.mapAlias());
			buf.writeInt(roomInfo.goal());
			buf.writeInt(roomInfo.timeLimit());
			buf.writeInt(roomInfo.weaponOption());
			buf.writeInt(roomInfo.ping());
			buf.writeInt(roomInfo.score1());
			buf.writeInt(roomInfo.score2());
			buf.writeInt(roomInfo.countryFilter());
			buf.writeBoolean(roomInfo.isBreakInto());
			buf.writeBoolean(roomInfo.isDropItem());
			buf.writeBoolean(roomInfo.isWanted());
			buf.writeInt(roomInfo.squad());
			buf.writeInt(roomInfo.squadCounter());
		}
	}
}