package de.brickforceaurora.gameserver.net.protocol.serverbound;

import de.brickforceaurora.gameserver.net.protocol.IServerboundPacket;
import io.netty.buffer.ByteBuf;

public final class ServerboundKillLogPacket implements IServerboundPacket {

	private byte killerType;
	private int killer;
	private byte victimType;
	private int victim;
	private int weaponBy;
	private int slot;
	private int category;
	private int hitpart;
	final String UnknownValue0 = "0";
	final String UnknownValue1 = "damageLog.Count";
	final String UnknownValue2 = "item.Key";
	final String UnknownValue3 = "item.Value";

	public final ServerboundKillLogPacket killerType(byte killerType) {
		this.killerType = killerType;
		return this;
	}

	public final byte killerType() {
		return this.killerType;
	}

	public final ServerboundKillLogPacket killer(int killer) {
		this.killer = killer;
		return this;
	}

	public final int killer() {
		return this.killer;
	}

	public final ServerboundKillLogPacket victimType(byte victimType) {
		this.victimType = victimType;
		return this;
	}

	public final byte victimType() {
		return this.victimType;
	}

	public final ServerboundKillLogPacket victim(int victim) {
		this.victim = victim;
		return this;
	}

	public final int victim() {
		return this.victim;
	}

	public final ServerboundKillLogPacket weaponBy(int weaponBy) {
		this.weaponBy = weaponBy;
		return this;
	}

	public final int weaponBy() {
		return this.weaponBy;
	}

	public final ServerboundKillLogPacket slot(int slot) {
		this.slot = slot;
		return this;
	}

	public final int slot() {
		return this.slot;
	}

	public final ServerboundKillLogPacket category(int category) {
		this.category = category;
		return this;
	}

	public final int category() {
		return this.category;
	}

	public final ServerboundKillLogPacket hitpart(int hitpart) {
		this.hitpart = hitpart;
		return this;
	}

	public final int hitpart() {
		return this.hitpart;
	}

	@Override
	public int packetId() {
		return 44;
	}

	@Override
	public final void read(ByteBuf buffer) {
		this.killerType = buffer.readByte();
		this.killer = buffer.readIntLE();
		this.victimType = buffer.readByte();
		this.victim = buffer.readIntLE();
		this.weaponBy = buffer.readIntLE();
		this.slot = buffer.readIntLE();
		this.category = buffer.readIntLE();
		this.hitpart = buffer.readIntLE();
	}
}