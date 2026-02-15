package de.brickforceaurora.server.net.protocol.serverbound.original;

import de.brickforceaurora.server.net.protocol.IServerboundPacket;
import de.brickforceaurora.server.net.protocol.PacketBuf;

public final class ServerboundLoginPacket implements IServerboundPacket {

	private String id;
	private String pswd;
	private int major;
	private int minor;
	private String privateIpAddress;
	private String macAddress;

	public final ServerboundLoginPacket id(String id) {
		this.id = id;
		return this;
	}

	public final String id() {
		return this.id;
	}

	public final ServerboundLoginPacket pswd(String pswd) {
		this.pswd = pswd;
		return this;
	}

	public final String pswd() {
		return this.pswd;
	}

	public final ServerboundLoginPacket major(int major) {
		this.major = major;
		return this;
	}

	public final int major() {
		return this.major;
	}

	public final ServerboundLoginPacket minor(int minor) {
		this.minor = minor;
		return this;
	}

	public final int minor() {
		return this.minor;
	}

	public final ServerboundLoginPacket privateIpAddress(String privateIpAddress) {
		this.privateIpAddress = privateIpAddress;
		return this;
	}

	public final String privateIpAddress() {
		return this.privateIpAddress;
	}

	public final ServerboundLoginPacket macAddress(String macAddress) {
		this.macAddress = macAddress;
		return this;
	}

	public final String macAddress() {
		return this.macAddress;
	}

	@Override
	public int packetId() {
		return 1;
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