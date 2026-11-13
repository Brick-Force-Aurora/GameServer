package de.brickforceaurora.server.net.protocol.clientbound.original;

import de.brickforceaurora.server.net.protocol.IClientboundPacket;
import de.brickforceaurora.server.net.protocol.PacketBuf;

public final class ClientboundGadgetActionPacket implements IClientboundPacket {

	private int gadget;
	private int action;

	public final ClientboundGadgetActionPacket gadget(int gadget) {
		this.gadget = gadget;
		return this;
	}

	public final int gadget() {
		return this.gadget;
	}

	public final ClientboundGadgetActionPacket action(int action) {
		this.action = action;
		return this;
	}

	public final int action() {
		return this.action;
	}

	@Override
	public int packetId() {
		return 403;
	}

	@Override
	public final void write(PacketBuf buf) {
		buf.writeInt(this.gadget);
		buf.writeInt(this.action);
	}
}
