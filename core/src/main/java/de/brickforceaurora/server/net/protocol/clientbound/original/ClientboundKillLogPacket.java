package de.brickforceaurora.server.net.protocol.clientbound.original;

import de.brickforceaurora.server.net.protocol.IClientboundPacket;
import de.brickforceaurora.server.net.protocol.PacketBuf;

public final class ClientboundKillLogPacket implements IClientboundPacket {

	private byte killerType;
	private int killer;
	private byte victimType;
	private int victim;
	private int weaponBy;
	private int hitpart;

	public final ClientboundKillLogPacket killerType(byte killerType) {
		this.killerType = killerType;
		return this;
	}

	public final byte killerType() {
		return this.killerType;
	}

	public final ClientboundKillLogPacket killer(int killer) {
		this.killer = killer;
		return this;
	}

	public final int killer() {
		return this.killer;
	}

	public final ClientboundKillLogPacket victimType(byte victimType) {
		this.victimType = victimType;
		return this;
	}

	public final byte victimType() {
		return this.victimType;
	}

	public final ClientboundKillLogPacket victim(int victim) {
		this.victim = victim;
		return this;
	}

	public final int victim() {
		return this.victim;
	}

	public final ClientboundKillLogPacket weaponBy(int weaponBy) {
		this.weaponBy = weaponBy;
		return this;
	}

	public final int weaponBy() {
		return this.weaponBy;
	}

	public final ClientboundKillLogPacket hitpart(int hitpart) {
		this.hitpart = hitpart;
		return this;
	}

	public final int hitpart() {
		return this.hitpart;
	}

	@Override
	public int packetId() {
		return 45;
	}

	@Override
	public final void write(PacketBuf buf) {
		buf.writeByte(this.killerType);
		buf.writeInt(this.killer);
		buf.writeByte(this.victimType);
		buf.writeInt(this.victim);
		buf.writeInt(this.weaponBy);
		buf.writeInt(this.hitpart);
	}
}
