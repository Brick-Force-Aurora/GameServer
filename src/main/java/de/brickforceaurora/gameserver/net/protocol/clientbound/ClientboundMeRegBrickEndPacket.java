package de.brickforceaurora.gameserver.net.protocol.clientbound;

import de.brickforceaurora.gameserver.net.protocol.IClientboundPacket;
import io.netty.buffer.ByteBuf;

public final class ClientboundMeRegBrickEndPacket implements IClientboundPacket {

	private boolean val;

	public final ClientboundMeRegBrickEndPacket val(boolean val) {
		this.val = val;
		return this;
	}

	public final boolean val() {
		return this.val;
	}

	@Override
	public int packetId() {
		return 341;
	}

	@Override
	public final void write(ByteBuf buffer) {
		buffer.writeBoolean(this.val);
	}
}