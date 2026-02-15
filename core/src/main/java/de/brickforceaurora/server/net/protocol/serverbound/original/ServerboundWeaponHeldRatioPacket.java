package de.brickforceaurora.server.net.protocol.serverbound.original;

import de.brickforceaurora.server.net.protocol.IServerboundPacket;
import de.brickforceaurora.server.net.protocol.PacketBuf;

public final class ServerboundWeaponHeldRatioPacket implements IServerboundPacket {

	final String UnknownValue0 = "weaponHeldRatio.Count";
	final String UnknownValue1 = "item.Key";
	final String UnknownValue2 = "item.Value";

	@Override
	public int packetId() {
		return 368;
	}

	@Override
	public final void read(PacketBuf buf) {
	}
}