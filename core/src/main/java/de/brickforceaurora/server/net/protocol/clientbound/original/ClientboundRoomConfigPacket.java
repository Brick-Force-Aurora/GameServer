package de.brickforceaurora.server.net.protocol.clientbound.original;

import de.brickforceaurora.server.net.protocol.IClientboundPacket;
import de.brickforceaurora.server.net.protocol.PacketBuf;
import de.brickforceaurora.server.net.protocol.data.RoomType;

public final class ClientboundRoomConfigPacket implements IClientboundPacket {

	private int mapId;
	private String mapAlias;
	private int weaponOption;
	private int timeLimit;
	private int killCount;
	private boolean canJoinMidGame;
	private boolean autoBalance;
	private boolean allowBuildGun;
	private String password;
	private int commented;
	private RoomType roomType;
	private boolean drop;
	private boolean wanted;

	public final ClientboundRoomConfigPacket mapId(int mapId) {
		this.mapId = mapId;
		return this;
	}

	public final int mapId() {
		return this.mapId;
	}

	public final ClientboundRoomConfigPacket mapAlias(String mapAlias) {
		this.mapAlias = mapAlias;
		return this;
	}

	public final String mapAlias() {
		return this.mapAlias;
	}

	public final ClientboundRoomConfigPacket weaponOption(int weaponOption) {
		this.weaponOption = weaponOption;
		return this;
	}

	public final int weaponOption() {
		return this.weaponOption;
	}

	public final ClientboundRoomConfigPacket timeLimit(int timeLimit) {
		this.timeLimit = timeLimit;
		return this;
	}

	public final int timeLimit() {
		return this.timeLimit;
	}

	public final ClientboundRoomConfigPacket killCount(int killCount) {
		this.killCount = killCount;
		return this;
	}

	public final int killCount() {
		return this.killCount;
	}

	public final ClientboundRoomConfigPacket canJoinMidGame(boolean canJoinMidGame) {
		this.canJoinMidGame = canJoinMidGame;
		return this;
	}

	public final boolean canJoinMidGame() {
		return this.canJoinMidGame;
	}

	public final ClientboundRoomConfigPacket autoBalance(boolean autoBalance) {
		this.autoBalance = autoBalance;
		return this;
	}

	public final boolean autoBalance() {
		return this.autoBalance;
	}

	public final ClientboundRoomConfigPacket allowBuildGun(boolean allowBuildGun) {
		this.allowBuildGun = allowBuildGun;
		return this;
	}

	public final boolean allowBuildGun() {
		return this.allowBuildGun;
	}

	public final ClientboundRoomConfigPacket password(String password) {
		this.password = password;
		return this;
	}

	public final String password() {
		return this.password;
	}

	public final ClientboundRoomConfigPacket commented(int commented) {
		if (commented > 255L || commented < 0L) {
			throw new IllegalArgumentException(
					"Value " + commented + " is out of bounds of allowed number range of 0 - 255");
		}
		this.commented = commented;
		return this;
	}

	public final int commented() {
		return this.commented;
	}

	public final ClientboundRoomConfigPacket roomType(RoomType roomType) {
		this.roomType = roomType;
		return this;
	}

	public final RoomType roomType() {
		return this.roomType;
	}

	public final ClientboundRoomConfigPacket drop(boolean drop) {
		this.drop = drop;
		return this;
	}

	public final boolean drop() {
		return this.drop;
	}

	public final ClientboundRoomConfigPacket wanted(boolean wanted) {
		this.wanted = wanted;
		return this;
	}

	public final boolean wanted() {
		return this.wanted;
	}

	@Override
	public int packetId() {
		return 92;
	}

	@Override
	public final void write(PacketBuf buf) {
		buf.writeInt(this.mapId);
		buf.writeString(this.mapAlias);
		buf.writeInt(this.weaponOption);
		buf.writeInt(this.timeLimit);
		buf.writeInt(this.killCount);
		buf.writeBoolean(this.canJoinMidGame);
		buf.writeBoolean(this.autoBalance);
		buf.writeBoolean(this.allowBuildGun);
		buf.writeString(this.password);
		buf.writeByte(this.commented);
		buf.writeInt(this.roomType.id());
		buf.writeBoolean(this.drop);
		buf.writeBoolean(this.wanted);
	}
}