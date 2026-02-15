package de.brickforceaurora.server.net.protocol.serverbound.original;

import de.brickforceaurora.server.net.protocol.IServerboundPacket;
import de.brickforceaurora.server.net.protocol.PacketBuf;

public final class ServerboundAutoLoginToRunupPacket implements IServerboundPacket {

	private String id;
	private String serial;
	private int clientId;
	private int major;
	private int minor;
	private String privateIpAddress;
	private String macAddress;

	public final ServerboundAutoLoginToRunupPacket id(String id) {
		this.id = id;
		return this;
	}

	public final String id() {
		return this.id;
	}

	public final ServerboundAutoLoginToRunupPacket serial(String serial) {
		this.serial = serial;
		return this;
	}

	public final String serial() {
		return this.serial;
	}

	public final ServerboundAutoLoginToRunupPacket clientId(int clientId) {
		this.clientId = clientId;
		return this;
	}

	public final int clientId() {
		return this.clientId;
	}

	public final ServerboundAutoLoginToRunupPacket major(int major) {
		this.major = major;
		return this;
	}

	public final int major() {
		return this.major;
	}

	public final ServerboundAutoLoginToRunupPacket minor(int minor) {
		this.minor = minor;
		return this;
	}

	public final int minor() {
		return this.minor;
	}

	public final ServerboundAutoLoginToRunupPacket privateIpAddress(String privateIpAddress) {
		this.privateIpAddress = privateIpAddress;
		return this;
	}

	public final String privateIpAddress() {
		return this.privateIpAddress;
	}

	public final ServerboundAutoLoginToRunupPacket macAddress(String macAddress) {
		this.macAddress = macAddress;
		return this;
	}

	public final String macAddress() {
		return this.macAddress;
	}

	@Override
	public int packetId() {
		return 409;
	}

	@Override
	public final void read(PacketBuf buf) {
		this.id = buf.readString();
		this.serial = buf.readString();
		this.clientId = buf.readInt();
		this.major = buf.readInt();
		this.minor = buf.readInt();
		this.privateIpAddress = buf.readString();
		this.macAddress = buf.readString();
	}
}