package de.brickforceaurora.server.net.protocol.serverbound.original;

import de.brickforceaurora.server.net.protocol.IServerboundPacket;
import de.brickforceaurora.server.net.protocol.PacketBuf;

public final class ServerboundLoginToAxeso5Packet implements IServerboundPacket {

	private String id;
	private String pswd;
	private int major;
	private int minor;
	private String privateIpAddress;
	private String macAddress;

	public final ServerboundLoginToAxeso5Packet id(String id) {
		this.id = id;
		return this;
	}

	public final String id() {
		return this.id;
	}

	public final ServerboundLoginToAxeso5Packet pswd(String pswd) {
		this.pswd = pswd;
		return this;
	}

	public final String pswd() {
		return this.pswd;
	}

	public final ServerboundLoginToAxeso5Packet major(int major) {
		this.major = major;
		return this;
	}

	public final int major() {
		return this.major;
	}

	public final ServerboundLoginToAxeso5Packet minor(int minor) {
		this.minor = minor;
		return this;
	}

	public final int minor() {
		return this.minor;
	}

	public final ServerboundLoginToAxeso5Packet privateIpAddress(String privateIpAddress) {
		this.privateIpAddress = privateIpAddress;
		return this;
	}

	public final String privateIpAddress() {
		return this.privateIpAddress;
	}

	public final ServerboundLoginToAxeso5Packet macAddress(String macAddress) {
		this.macAddress = macAddress;
		return this;
	}

	public final String macAddress() {
		return this.macAddress;
	}

	@Override
	public int packetId() {
		return 504;
	}

	@Override
	public final void read(PacketBuf buf) {
		this.id = buf.readString();
		this.pswd = buf.readString();
		this.major = buf.readInt();
		this.minor = buf.readInt();
		this.privateIpAddress = buf.readString();
		this.macAddress = buf.readString();
	}
}