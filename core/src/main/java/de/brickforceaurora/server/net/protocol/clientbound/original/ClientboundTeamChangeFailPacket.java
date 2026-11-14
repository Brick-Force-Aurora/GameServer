package de.brickforceaurora.server.net.protocol.clientbound.original;

import de.brickforceaurora.server.net.protocol.IClientboundPacket;
import de.brickforceaurora.server.net.protocol.PacketBuf;

public final class ClientboundTeamChangeFailPacket implements IClientboundPacket {

	private int reason;

	public final ClientboundTeamChangeFailPacket reason(int reason) {
		this.reason = reason;
		return this;
	}

	public final int reason() {
		return this.reason;
	}

	@Override
	public int packetId() {
		return 87;
	}

	@Override
	public final void write(PacketBuf buf) {
		buf.writeInt(this.reason);
	}
}