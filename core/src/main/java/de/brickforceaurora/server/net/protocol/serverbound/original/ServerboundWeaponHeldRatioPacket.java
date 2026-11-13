package de.brickforceaurora.server.net.protocol.serverbound.original;

import de.brickforceaurora.server.net.protocol.IServerboundPacket;
import de.brickforceaurora.server.net.protocol.PacketBuf;
import de.brickforceaurora.server.net.protocol.data.WeaponRatio;

public final class ServerboundWeaponHeldRatioPacket implements IServerboundPacket {

	private WeaponRatio[] heldRatio = new WeaponRatio[0];

	public final ServerboundWeaponHeldRatioPacket heldRatio(WeaponRatio[] heldRatio) {
		this.heldRatio = heldRatio;
		return this;
	}

	public final WeaponRatio[] heldRatio() {
		return this.heldRatio;
	}

	@Override
	public int packetId() {
		return 368;
	}

	@Override
	public final void read(PacketBuf buf) {
		this.heldRatio = buf.readArray(WeaponRatio[]::new, WeaponRatio::fromBuffer);
	}
}
