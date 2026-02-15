package de.brickforceaurora.server.net.protocol.clientbound.original;

import de.brickforceaurora.server.net.protocol.IClientboundPacket;
import de.brickforceaurora.server.net.protocol.PacketBuf;

public final class ClientboundAddBanFailPacket implements IClientboundPacket {

	private int Unnamed0;

	public final ClientboundAddBanFailPacket Unnamed0(int Unnamed0) {
		this.Unnamed0 = Unnamed0;
		return this;
	}

	public final int Unnamed0() {
		return this.Unnamed0;
	}

	@Override
	public int packetId() {
		return 115;
	}

	@Override
	public final void write(PacketBuf buf) {
		buf.writeInt(this.Unnamed0);
	}
}