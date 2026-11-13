package de.brickforceaurora.server.net.protocol.clientbound.original;

import de.brickforceaurora.server.net.protocol.IClientboundPacket;
import de.brickforceaurora.server.net.protocol.PacketBuf;

public final class ClientboundBreakIntoPacket implements IClientboundPacket {

	private int result;

	public final ClientboundBreakIntoPacket result(int result) {
		this.result = result;
		return this;
	}

	public final int result() {
		return this.result;
	}

	@Override
	public int packetId() {
		return 74;
	}

	@Override
	public final void write(PacketBuf buf) {
		buf.writeInt(this.result);
	}
}
