package de.brickforceaurora.gameserver.net.protocol.serverbound;

import de.brickforceaurora.gameserver.net.protocol.IServerboundPacket;
import io.netty.buffer.ByteBuf;

public final class ServerboundBatchDelBrickPacket implements IServerboundPacket {

	final String UnknownValue0 = "bricks.Length";
	final String UnknownValue1 = "bricks[i]";

	@Override
	public int packetId() {
		return 479;
	}

	@Override
	public final void read(ByteBuf buffer) {
	}
}