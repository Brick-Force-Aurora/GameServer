package de.brickforceaurora.gameserver.net.protocol.serverbound;

import de.brickforceaurora.gameserver.net.protocol.IServerboundPacket;
import io.netty.buffer.ByteBuf;

public final class ServerboundWeaponHeldRatioPacket implements IServerboundPacket {

	final String UnknownValue0 = "weaponHeldRatio.Count";
	final String UnknownValue1 = "item.Key";
	final String UnknownValue2 = "item.Value";

	@Override
	public int packetId() {
		return 368;
	}

	@Override
	public final void read(ByteBuf buffer) {
	}
}