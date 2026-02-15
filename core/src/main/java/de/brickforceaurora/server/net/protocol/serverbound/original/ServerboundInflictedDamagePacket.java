package de.brickforceaurora.server.net.protocol.serverbound.original;

import de.brickforceaurora.server.net.protocol.IServerboundPacket;
import de.brickforceaurora.server.net.protocol.PacketBuf;

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
	public final void read(PacketBuf buf) {
	}
}