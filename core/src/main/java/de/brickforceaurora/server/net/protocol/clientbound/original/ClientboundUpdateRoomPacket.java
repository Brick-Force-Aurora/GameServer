package de.brickforceaurora.server.net.protocol.clientbound.original;

import de.brickforceaurora.server.net.protocol.IClientboundPacket;
import de.brickforceaurora.server.net.protocol.PacketBuf;
import de.brickforceaurora.server.net.protocol.data.api.IRoomInfo;

public final class ClientboundUpdateRoomPacket implements IClientboundPacket {

	private IRoomInfo roomInfo;

	public final ClientboundUpdateRoomPacket roomInfo(IRoomInfo roomInfo) {
		this.roomInfo = roomInfo;
		return this;
	}

	public final IRoomInfo roomInfo() {
		return this.roomInfo;
	}

	@Override
	public int packetId() {
		return 30;
	}

	@Override
	public final void write(PacketBuf buf) {
		buf.writeInt(this.roomInfo.id());
		buf.writeInt(this.roomInfo.status().id());
		buf.writeInt(this.roomInfo.players());
		buf.writeInt(this.roomInfo.maxPlayers());
		buf.writeBoolean(this.roomInfo.passwordLocked());
		buf.writeInt(this.roomInfo.mapId()); //TODO: If type = BND && Status = Playing always return 0
		buf.writeString(this.roomInfo.mapAlias());
		buf.writeInt(this.roomInfo.goal());
		buf.writeInt(this.roomInfo.timeLimit());
		buf.writeInt(this.roomInfo.weaponOption());
		buf.writeInt(this.roomInfo.ping());
		buf.writeInt(this.roomInfo.blueScore());
		buf.writeInt(this.roomInfo.redScore());
		buf.writeInt(this.roomInfo.countryFilter().id());
		buf.writeBoolean(this.roomInfo.allowsLateJoining());
		buf.writeInt(this.roomInfo.type().id());
		buf.writeString(this.roomInfo.title());
		buf.writeBoolean(this.roomInfo.weaponDropEnabled());
		buf.writeBoolean(this.roomInfo.wantedEnabled());
		buf.writeInt(this.roomInfo.squad());
		buf.writeInt(this.roomInfo.squadCounter());
	}
}