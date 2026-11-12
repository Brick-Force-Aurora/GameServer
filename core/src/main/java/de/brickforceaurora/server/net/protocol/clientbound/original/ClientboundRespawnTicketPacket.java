package de.brickforceaurora.server.net.protocol.clientbound.original;

import de.brickforceaurora.server.net.protocol.IClientboundPacket;
import de.brickforceaurora.server.net.protocol.PacketBuf;

public final class ClientboundRespawnTicketPacket implements IClientboundPacket {

	private int ticket;

	public final ClientboundRespawnTicketPacket ticket(int ticket) {
		this.ticket = ticket;
		return this;
	}

	public final int ticket() {
		return this.ticket;
	}

	@Override
	public int packetId() {
		return 64;
	}

	@Override
	public final void write(PacketBuf buf) {
		buf.writeInt(this.ticket);
	}
}