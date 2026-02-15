package de.brickforceaurora.server.net.protocol.serverbound.original;

import de.brickforceaurora.server.net.protocol.IServerboundPacket;
import de.brickforceaurora.server.net.protocol.PacketBuf;

public final class ServerboundRoomConfigPacket implements IServerboundPacket {

	private int killCount;
	private int timeLimit;
	private int weaponOption;
	private int nWhere;
	private int breakInto;
	private int teamBalance;
	private int useBuildGun;
	private int itemPickup;
	private String whereAlias;
	private String pswd;
	private int type;

	public final ServerboundRoomConfigPacket killCount(int killCount) {
		this.killCount = killCount;
		return this;
	}

	public final int killCount() {
		return this.killCount;
	}

	public final ServerboundRoomConfigPacket timeLimit(int timeLimit) {
		this.timeLimit = timeLimit;
		return this;
	}

	public final int timeLimit() {
		return this.timeLimit;
	}

	public final ServerboundRoomConfigPacket weaponOption(int weaponOption) {
		this.weaponOption = weaponOption;
		return this;
	}

	public final int weaponOption() {
		return this.weaponOption;
	}

	public final ServerboundRoomConfigPacket nWhere(int nWhere) {
		this.nWhere = nWhere;
		return this;
	}

	public final int nWhere() {
		return this.nWhere;
	}

	public final ServerboundRoomConfigPacket breakInto(int breakInto) {
		this.breakInto = breakInto;
		return this;
	}

	public final int breakInto() {
		return this.breakInto;
	}

	public final ServerboundRoomConfigPacket teamBalance(int teamBalance) {
		this.teamBalance = teamBalance;
		return this;
	}

	public final int teamBalance() {
		return this.teamBalance;
	}

	public final ServerboundRoomConfigPacket useBuildGun(int useBuildGun) {
		this.useBuildGun = useBuildGun;
		return this;
	}

	public final int useBuildGun() {
		return this.useBuildGun;
	}

	public final ServerboundRoomConfigPacket itemPickup(int itemPickup) {
		this.itemPickup = itemPickup;
		return this;
	}

	public final int itemPickup() {
		return this.itemPickup;
	}

	public final ServerboundRoomConfigPacket whereAlias(String whereAlias) {
		this.whereAlias = whereAlias;
		return this;
	}

	public final String whereAlias() {
		return this.whereAlias;
	}

	public final ServerboundRoomConfigPacket pswd(String pswd) {
		this.pswd = pswd;
		return this;
	}

	public final String pswd() {
		return this.pswd;
	}

	public final ServerboundRoomConfigPacket type(int type) {
		this.type = type;
		return this;
	}

	public final int type() {
		return this.type;
	}

	@Override
	public int packetId() {
		return 91;
	}

	@Override
	public final void read(PacketBuf buf) {
		this.killCount = buf.readInt();
		this.timeLimit = buf.readInt();
		this.weaponOption = buf.readInt();
		this.nWhere = buf.readInt();
		this.breakInto = buf.readInt();
		this.teamBalance = buf.readInt();
		this.useBuildGun = buf.readInt();
		this.itemPickup = buf.readInt();
		this.whereAlias = buf.readString();
		this.pswd = buf.readString();
		this.type = buf.readInt();
	}
}