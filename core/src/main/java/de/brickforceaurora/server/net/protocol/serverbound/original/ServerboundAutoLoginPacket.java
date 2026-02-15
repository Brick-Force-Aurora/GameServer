package de.brickforceaurora.server.net.protocol.serverbound.original;

import de.brickforceaurora.server.net.protocol.IServerboundPacket;
import de.brickforceaurora.server.net.protocol.PacketBuf;

public final class ServerboundAutoLoginPacket implements IServerboundPacket {

	private String token;
	private int major;
	private int minor;
	private String privateIpAddress;
	private String macAddress;

	public final ServerboundAutoLoginPacket token(String token) {
		this.token = token;
		return this;
	}

	public final String token() {
		return this.token;
	}

	public final ServerboundAutoLoginPacket major(int major) {
		this.major = major;
		return this;
	}

	public final int major() {
		return this.major;
	}

	public final ServerboundAutoLoginPacket minor(int minor) {
		this.minor = minor;
		return this;
	}

	public final int minor() {
		return this.minor;
	}

	public final ServerboundAutoLoginPacket privateIpAddress(String privateIpAddress) {
		this.privateIpAddress = privateIpAddress;
		return this;
	}

	public final String privateIpAddress() {
		return this.privateIpAddress;
	}

	public final ServerboundAutoLoginPacket macAddress(String macAddress) {
		this.macAddress = macAddress;
		return this;
	}

	public final String macAddress() {
		return this.macAddress;
	}

	@Override
	public int packetId() {
		return 393;
	}

	@Override
	public final void read(PacketBuf buf) {
		this.token = buf.readString();
		this.major = buf.readInt();
		this.minor = buf.readInt();
		this.privateIpAddress = buf.readString();
		this.macAddress = buf.readString();
	}
}