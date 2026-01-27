package de.brickforceaurora.gameserver.net.protocol.serverbound;

import de.brickforceaurora.gameserver.net.protocol.IServerboundPacket;
import io.netty.buffer.ByteBuf;
import java.nio.charset.StandardCharsets;

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
	public final void read(ByteBuf buffer) {
		{
			int length = buffer.readIntLE();
			if (length == 0) {
				this.id = "";
			} else {
				byte[] bytes = new byte[length];
				buffer.readBytes(bytes);
				this.id = new String(bytes, StandardCharsets.UTF_16LE);
			}
		}
		{
			int length = buffer.readIntLE();
			if (length == 0) {
				this.serial = "";
			} else {
				byte[] bytes = new byte[length];
				buffer.readBytes(bytes);
				this.serial = new String(bytes, StandardCharsets.UTF_16LE);
			}
		}
		this.clientId = buffer.readIntLE();
		this.major = buffer.readIntLE();
		this.minor = buffer.readIntLE();
		{
			int length = buffer.readIntLE();
			if (length == 0) {
				this.privateIpAddress = "";
			} else {
				byte[] bytes = new byte[length];
				buffer.readBytes(bytes);
				this.privateIpAddress = new String(bytes, StandardCharsets.UTF_16LE);
			}
		}
		{
			int length = buffer.readIntLE();
			if (length == 0) {
				this.macAddress = "";
			} else {
				byte[] bytes = new byte[length];
				buffer.readBytes(bytes);
				this.macAddress = new String(bytes, StandardCharsets.UTF_16LE);
			}
		}
	}
}