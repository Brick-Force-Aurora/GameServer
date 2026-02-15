package de.brickforceaurora.server.net.protocol.serverbound.original;

import de.brickforceaurora.server.net.protocol.IServerboundPacket;
import de.brickforceaurora.server.net.protocol.PacketBuf;

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
	public final void read(PacketBuf buf) {
		this.clientId = buf.readInt();
		this.action = buf.readInt();
	}
}