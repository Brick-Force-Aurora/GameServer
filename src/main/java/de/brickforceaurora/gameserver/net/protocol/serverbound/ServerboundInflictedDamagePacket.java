package de.brickforceaurora.gameserver.net.protocol.serverbound;

import de.brickforceaurora.gameserver.net.protocol.IServerboundPacket;
import io.netty.buffer.ByteBuf;

public final class ServerboundInflictedDamagePacket implements IServerboundPacket {

	final String UnknownValue0 = "0";
	final String UnknownValue1 = "inflictedDamage.Count";
	final String UnknownValue2 = "item.Key";
	final String UnknownValue3 = "item.Value";

	@Override
	public int packetId() {
		return 399;
	}

	@Override
	public final void read(ByteBuf buffer) {
	}
}