package de.brickforceaurora.gameserver.net.protocol.serverbound;

import de.brickforceaurora.gameserver.net.protocol.IServerboundPacket;
import io.netty.buffer.ByteBuf;
import java.nio.charset.StandardCharsets;

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
				this.pswd = "";
			} else {
				byte[] bytes = new byte[length];
				buffer.readBytes(bytes);
				this.pswd = new String(bytes, StandardCharsets.UTF_16LE);
			}
		}
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