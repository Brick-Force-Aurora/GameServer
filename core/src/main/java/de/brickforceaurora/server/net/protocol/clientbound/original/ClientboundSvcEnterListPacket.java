package de.brickforceaurora.server.net.protocol.clientbound.original;

import de.brickforceaurora.server.net.protocol.IClientboundPacket;
import de.brickforceaurora.server.net.protocol.PacketBuf;
import de.brickforceaurora.server.net.protocol.data.ClientInfo;

public final class ClientboundSvcEnterListPacket implements IClientboundPacket {

	private ClientInfo[] clients;

	public final ClientboundSvcEnterListPacket clients(ClientInfo[] clients) {
		this.clients = clients;
		return this;
	}

	public final ClientInfo[] clients() {
		return this.clients;
	}

	@Override
	public int packetId() {
		return 467;
	}

	@Override
	public final void write(PacketBuf buf) {
		buf.writeInt(this.clients.length);
		for (ClientInfo client : this.clients){
			buf.writeInt(client.seq());
			buf.writeString(client.name());
			buf.writeInt(client.xp());
			buf.writeInt(client.rank());
		}
	}
}