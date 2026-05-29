package de.brickforceaurora.server.net.protocol.clientbound.original;

import de.brickforceaurora.server.net.protocol.IClientboundPacket;
import de.brickforceaurora.server.net.protocol.PacketBuf;

public final class ClientboundRendezvousInfoPacket implements IClientboundPacket {

	private String ip;
	private int port;

	public final ClientboundRendezvousInfoPacket ip(String ip) {
		this.ip = ip;
		return this;
	}

	public final String ip() {
		return this.ip;
	}

	public final ClientboundRendezvousInfoPacket port(int port) {
		this.port = port;
		return this;
	}

	public final int port() {
		return this.port;
	}

	@Override
	public int packetId() {
		return 320;
	}

	@Override
	public final void write(PacketBuf buf) {
		buf.writeInt(0); //unused
		buf.writeString(this.ip);
		buf.writeInt(this.port);
	}
}