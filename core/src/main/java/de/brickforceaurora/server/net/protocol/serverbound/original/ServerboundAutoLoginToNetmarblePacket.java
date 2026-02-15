package de.brickforceaurora.server.net.protocol.serverbound.original;

import de.brickforceaurora.server.net.protocol.IServerboundPacket;
import de.brickforceaurora.server.net.protocol.PacketBuf;

public final class ServerboundAutoLoginToNetmarblePacket implements IServerboundPacket {

	final String UnknownValue0 = "CPCookie[i]";
	private int major;
	private int minor;
	private String privateIpAddress;
	private String macAddress;

	public final ServerboundAutoLoginToNetmarblePacket major(int major) {
		this.major = major;
		return this;
	}

	public final int major() {
		return this.major;
	}

	public final ServerboundAutoLoginToNetmarblePacket minor(int minor) {
		this.minor = minor;
		return this;
	}

	public final int minor() {
		return this.minor;
	}

	public final ServerboundAutoLoginToNetmarblePacket privateIpAddress(String privateIpAddress) {
		this.privateIpAddress = privateIpAddress;
		return this;
	}

	public final String privateIpAddress() {
		return this.privateIpAddress;
	}

	public final ServerboundAutoLoginToNetmarblePacket macAddress(String macAddress) {
		this.macAddress = macAddress;
		return this;
	}

	public final String macAddress() {
		return this.macAddress;
	}

	@Override
	public int packetId() {
		return 458;
	}

	@Override
	public final void read(PacketBuf buf) {
		this.major = buf.readInt();
		this.minor = buf.readInt();
		this.privateIpAddress = buf.readString();
		this.macAddress = buf.readString();
	}
}