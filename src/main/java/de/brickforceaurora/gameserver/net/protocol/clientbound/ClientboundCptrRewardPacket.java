package de.brickforceaurora.gameserver.net.protocol.clientbound;

import de.brickforceaurora.gameserver.net.protocol.IClientboundPacket;
import io.netty.buffer.ByteBuf;

public final class ClientboundCptrRewardPacket implements IClientboundPacket {

	private int val;

	public final ClientboundCptrRewardPacket val(int val) {
		this.val = val;
		return this;
	}

	public final int val() {
		return this.val;
	}

	@Override
	public int packetId() {
		return 488;
	}

	@Override
	public final void write(ByteBuf buffer) {
		buffer.writeIntLE(this.val);
	}
}