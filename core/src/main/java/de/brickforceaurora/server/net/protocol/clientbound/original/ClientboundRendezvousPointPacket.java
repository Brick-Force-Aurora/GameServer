package de.brickforceaurora.server.net.protocol.clientbound.original;

import de.brickforceaurora.server.net.protocol.IClientboundPacket;
import de.brickforceaurora.server.net.protocol.PacketBuf;

public final class ClientboundRendezvousPointPacket implements IClientboundPacket {

	private int clientId;
	private String localIp;
	private int localPort;
	private String remoteIp;
	private int remotePort;

	public final ClientboundRendezvousPointPacket clientId(int clientId) {
		this.clientId = clientId;
		return this;
	}

	public final int clientId() {
		return this.clientId;
	}

	public final ClientboundRendezvousPointPacket localIp(String localIp) {
		this.localIp = localIp;
		return this;
	}

	public final String localIp() {
		return this.localIp;
	}

	public final ClientboundRendezvousPointPacket localPort(int localPort) {
		this.localPort = localPort;
		return this;
	}

	public final int localPort() {
		return this.localPort;
	}

	public final ClientboundRendezvousPointPacket remoteIp(String remoteIp) {
		this.remoteIp = remoteIp;
		return this;
	}

	public final String remoteIp() {
		return this.remoteIp;
	}

	public final ClientboundRendezvousPointPacket remotePort(int remotePort) {
		this.remotePort = remotePort;
		return this;
	}

	public final int remotePort() {
		return this.remotePort;
	}

	@Override
	public int packetId() {
		return 153;
	}

	@Override
	public final void write(PacketBuf buf) {
		buf.writeInt(this.clientId);
		buf.writeString(this.localIp);
		buf.writeInt(this.localPort);
		buf.writeString(this.remoteIp);
		buf.writeInt(this.remotePort);
	}
}