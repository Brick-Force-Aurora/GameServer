package de.brickforceaurora.gameserver.net.protocol.serverbound;

import de.brickforceaurora.gameserver.net.protocol.IServerboundPacket;
import io.netty.buffer.ByteBuf;

public final class ServerboundGadgetActionPacket implements IServerboundPacket {

	private int clientId;
	private int action;

	public final ServerboundGadgetActionPacket clientId(int clientId) {
		this.clientId = clientId;
		return this;
	}

	public final int clientId() {
		return this.clientId;
	}

	public final ServerboundGadgetActionPacket action(int action) {
		this.action = action;
		return this;
	}

	public final int action() {
		return this.action;
	}

	@Override
	public int packetId() {
		return 402;
	}

	@Override
	public final void read(ByteBuf buffer) {
		this.clientId = buffer.readIntLE();
		this.action = buffer.readIntLE();
	}
}