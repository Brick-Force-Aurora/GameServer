package de.brickforceaurora.server.net.protocol.serverbound.original;

import de.brickforceaurora.server.net.protocol.IServerboundPacket;
import de.brickforceaurora.server.net.protocol.PacketBuf;

public final class ServerboundGadgetActionPacket implements IServerboundPacket {

	private int gadget;
	private int action;

	public final ServerboundGadgetActionPacket gadget(int gadget) {
		this.gadget = gadget;
		return this;
	}

	public final int gadget() {
		return this.gadget;
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
		this.gadget = buf.readInt();
		this.action = buf.readInt();
	}
}
