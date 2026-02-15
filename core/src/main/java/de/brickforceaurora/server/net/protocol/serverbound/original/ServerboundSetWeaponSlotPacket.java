package de.brickforceaurora.server.net.protocol.serverbound.original;

import de.brickforceaurora.server.net.protocol.IServerboundPacket;
import de.brickforceaurora.server.net.protocol.PacketBuf;

public final class ServerboundSetWeaponSlotPacket implements IServerboundPacket {

	private int slot;
	private long weapon;

	public final ServerboundSetWeaponSlotPacket slot(int slot) {
		this.slot = slot;
		return this;
	}

	public final int slot() {
		return this.slot;
	}

	public final ServerboundSetWeaponSlotPacket weapon(long weapon) {
		this.weapon = weapon;
		return this;
	}

	public final long weapon() {
		return this.weapon;
	}

	@Override
	public int packetId() {
		return 419;
	}

	@Override
	public final void read(PacketBuf buf) {
		this.slot = buf.readInt();
		this.weapon = buf.readLong();
	}
}