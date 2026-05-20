package de.brickforceaurora.server.net.protocol.clientbound.original;

import de.brickforceaurora.server.net.protocol.IClientboundPacket;
import de.brickforceaurora.server.net.protocol.PacketBuf;
import de.brickforceaurora.server.net.protocol.data.api.IClientInfo;

public final class ClientboundSvcEnterListPacket implements IClientboundPacket {

	private IClientInfo[] clients;

	public final ClientboundSvcEnterListPacket clients(IClientInfo[] clients) {
		this.clients = clients;
		return this;
	}

	public final IClientInfo[] clients() {
		return this.clients;
	}

	@Override
	public int packetId() {
		return 467;
	}

	@Override
	public final void write(PacketBuf buf) {
	    buf.writeArray(clients, IClientInfo::toBuffer);
	}
}