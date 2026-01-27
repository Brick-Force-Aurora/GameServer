package de.brickforceaurora.gameserver.net.protocol.serverbound;

import de.brickforceaurora.gameserver.net.protocol.IServerboundPacket;
import io.netty.buffer.ByteBuf;

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
	public final void read(ByteBuf buffer) {
		this.slot = buffer.readIntLE();
		this.weapon = buffer.readLongLE();
	}
}