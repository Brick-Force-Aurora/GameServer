package de.brickforceaurora.server.net.protocol.serverbound.original;

import de.brickforceaurora.server.net.protocol.IServerboundPacket;
import de.brickforceaurora.server.net.protocol.PacketBuf;

public final class ServerboundMatchTeamStartPacket implements IServerboundPacket {

	private int mode;
	private int maxPlayer;
	private int map;
	private int killCount;
	private int timeLimit;
	private int weaponOption;
	private String alias;
	private boolean wanted;

	public final ServerboundMatchTeamStartPacket mode(int mode) {
		this.mode = mode;
		return this;
	}

	public final int mode() {
		return this.mode;
	}

	public final ServerboundMatchTeamStartPacket maxPlayer(int maxPlayer) {
		this.maxPlayer = maxPlayer;
		return this;
	}

	public final int maxPlayer() {
		return this.maxPlayer;
	}

	public final ServerboundMatchTeamStartPacket map(int map) {
		this.map = map;
		return this;
	}

	public final int map() {
		return this.map;
	}

	public final ServerboundMatchTeamStartPacket killCount(int killCount) {
		this.killCount = killCount;
		return this;
	}

	public final int killCount() {
		return this.killCount;
	}

	public final ServerboundMatchTeamStartPacket timeLimit(int timeLimit) {
		this.timeLimit = timeLimit;
		return this;
	}

	public final int timeLimit() {
		return this.timeLimit;
	}

	public final ServerboundMatchTeamStartPacket weaponOption(int weaponOption) {
		this.weaponOption = weaponOption;
		return this;
	}

	public final int weaponOption() {
		return this.weaponOption;
	}

	public final ServerboundMatchTeamStartPacket alias(String alias) {
		this.alias = alias;
		return this;
	}

	public final String alias() {
		return this.alias;
	}

	public final ServerboundMatchTeamStartPacket wanted(boolean wanted) {
		this.wanted = wanted;
		return this;
	}

	public final boolean wanted() {
		return this.wanted;
	}

	@Override
	public int packetId() {
		return 251;
	}

	@Override
	public final void read(PacketBuf buf) {
		this.mode = buf.readInt();
		this.maxPlayer = buf.readInt();
		this.map = buf.readInt();
		this.killCount = buf.readInt();
		this.timeLimit = buf.readInt();
		this.weaponOption = buf.readInt();
		this.alias = buf.readString();
		this.wanted = buf.readBoolean();
	}
}