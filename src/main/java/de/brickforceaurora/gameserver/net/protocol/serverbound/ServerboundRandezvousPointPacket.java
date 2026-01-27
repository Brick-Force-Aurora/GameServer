package de.brickforceaurora.gameserver.net.protocol.serverbound;

import de.brickforceaurora.gameserver.net.protocol.IServerboundPacket;
import io.netty.buffer.ByteBuf;
import java.nio.charset.StandardCharsets;

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
	public final void read(ByteBuf buffer) {
		{
			int length = buffer.readIntLE();
			if (length == 0) {
				this.localIp = "";
			} else {
				byte[] bytes = new byte[length];
				buffer.readBytes(bytes);
				this.localIp = new String(bytes, StandardCharsets.UTF_16LE);
			}
		}
		this.localPort = buffer.readIntLE();
		{
			int length = buffer.readIntLE();
			if (length == 0) {
				this.remoteIp = "";
			} else {
				byte[] bytes = new byte[length];
				buffer.readBytes(bytes);
				this.remoteIp = new String(bytes, StandardCharsets.UTF_16LE);
			}
		}
		this.remotePort = buffer.readIntLE();
	}
}