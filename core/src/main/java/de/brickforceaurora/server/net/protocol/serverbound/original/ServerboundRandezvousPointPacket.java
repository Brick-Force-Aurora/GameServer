package de.brickforceaurora.server.net.protocol.serverbound.original;

import de.brickforceaurora.server.net.protocol.IServerboundPacket;
import de.brickforceaurora.server.net.protocol.PacketBuf;

public final class ServerboundRandezvousPointPacket implements IServerboundPacket {

	private String localIp;
	private int localPort;
	private String remoteIp;
	private int remotePort;

	public final ServerboundRandezvousPointPacket localIp(String localIp) {
		this.localIp = localIp;
		return this;
	}

	public final String localIp() {
		return this.localIp;
	}

	public final ServerboundRandezvousPointPacket localPort(int localPort) {
		this.localPort = localPort;
		return this;
	}

	public final int localPort() {
		return this.localPort;
	}

	public final ServerboundRandezvousPointPacket remoteIp(String remoteIp) {
		this.remoteIp = remoteIp;
		return this;
	}

	public final String remoteIp() {
		return this.remoteIp;
	}

	public final ServerboundRandezvousPointPacket remotePort(int remotePort) {
		this.remotePort = remotePort;
		return this;
	}

	public final int remotePort() {
		return this.remotePort;
	}

	@Override
	public int packetId() {
		return 83;
	}

	@Override
	public final void read(PacketBuf buf) {
		this.localIp = buf.readString();
		this.localPort = buf.readInt();
		this.remoteIp = buf.readString();
		this.remotePort = buf.readInt();
	}
}