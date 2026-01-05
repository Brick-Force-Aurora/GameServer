package de.brickforceaurora.gameserver.net.protocol.clientbound;

import de.brickforceaurora.gameserver.net.protocol.IClientboundPacket;
import io.netty.buffer.ByteBuf;

public final class ClientboundPlayerInitInfoPacket implements IClientboundPacket {

	private int xp;
	private byte tutorialFlag;
	private int country;
	private boolean tosAccepted;
	private int extraSlots;
	private int rank;
	private int firstLoginForcePoints;

	public final ClientboundPlayerInitInfoPacket xp(int xp) {
		this.xp = xp;
		return this;
	}

	public final int xp() {
		return this.xp;
	}

	public final ClientboundPlayerInitInfoPacket tutorialFlag(byte tutorialFlag) {
		this.tutorialFlag = tutorialFlag;
		return this;
	}

	public final byte tutorialFlag() {
		return this.tutorialFlag;
	}

	public final ClientboundPlayerInitInfoPacket country(int country) {
		this.country = country;
		return this;
	}

	public final int country() {
		return this.country;
	}

	public final ClientboundPlayerInitInfoPacket tosAccepted(boolean tosAccepted) {
		this.tosAccepted = tosAccepted;
		return this;
	}

	public final boolean tosAccepted() {
		return this.tosAccepted;
	}

	public final ClientboundPlayerInitInfoPacket extraSlots(int extraSlots) {
		this.extraSlots = extraSlots;
		return this;
	}

	public final int extraSlots() {
		return this.extraSlots;
	}

	public final ClientboundPlayerInitInfoPacket rank(int rank) {
		this.rank = rank;
		return this;
	}

	public final int rank() {
		return this.rank;
	}

	public final ClientboundPlayerInitInfoPacket firstLoginForcePoints(int firstLoginForcePoints) {
		this.firstLoginForcePoints = firstLoginForcePoints;
		return this;
	}

	public final int firstLoginForcePoints() {
		return this.firstLoginForcePoints;
	}

	@Override
	public int packetId() {
		return 148;
	}

	@Override
	public final void write(ByteBuf buffer) {
		buffer.writeIntLE(this.xp);
		buffer.writeByte(this.tutorialFlag);
		buffer.writeIntLE(this.country);
		buffer.writeBoolean(this.tosAccepted);
		buffer.writeIntLE(this.extraSlots);
		buffer.writeIntLE(this.rank);
		buffer.writeIntLE(this.firstLoginForcePoints);
	}
}