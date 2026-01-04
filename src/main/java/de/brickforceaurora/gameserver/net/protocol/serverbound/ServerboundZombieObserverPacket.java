package de.brickforceaurora.gameserver.net.protocol.serverbound;

import de.brickforceaurora.gameserver.net.protocol.IServerboundPacket;
import io.netty.buffer.ByteBuf;

public final class ServerboundZombieObserverPacket implements IServerboundPacket {

	final String UnknownValue0 = "MyInfoManager.Instance.Seq";

	@Override
	public int packetId() {
		return 549;
	}

	@Override
	public final void read(ByteBuf buffer) {
	}
}