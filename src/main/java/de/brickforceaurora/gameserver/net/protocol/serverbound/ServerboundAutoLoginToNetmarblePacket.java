package de.brickforceaurora.gameserver.net.protocol.serverbound;

import de.brickforceaurora.gameserver.net.protocol.IServerboundPacket;
import io.netty.buffer.ByteBuf;
import java.nio.charset.StandardCharsets;

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
	public final void read(ByteBuf buffer) {
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