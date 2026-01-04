package de.brickforceaurora.gameserver.net.protocol.serverbound;

import de.brickforceaurora.gameserver.net.protocol.IServerboundPacket;
import io.netty.buffer.ByteBuf;
import java.nio.charset.StandardCharsets;

public final class ServerboundUpgradeItemPacket implements IServerboundPacket {

	private long item;
	private String code;
	private long upgrader;
	private String upgradercode;
	private int prop;

	public final ServerboundUpgradeItemPacket item(long item) {
		this.item = item;
		return this;
	}

	public final long item() {
		return this.item;
	}

	public final ServerboundUpgradeItemPacket code(String code) {
		this.code = code;
		return this;
	}

	public final String code() {
		return this.code;
	}

	public final ServerboundUpgradeItemPacket upgrader(long upgrader) {
		this.upgrader = upgrader;
		return this;
	}

	public final long upgrader() {
		return this.upgrader;
	}

	public final ServerboundUpgradeItemPacket upgradercode(String upgradercode) {
		this.upgradercode = upgradercode;
		return this;
	}

	public final String upgradercode() {
		return this.upgradercode;
	}

	public final ServerboundUpgradeItemPacket prop(int prop) {
		this.prop = prop;
		return this;
	}

	public final int prop() {
		return this.prop;
	}

	@Override
	public int packetId() {
		return 353;
	}

	@Override
	public final void read(ByteBuf buffer) {
		this.item = buffer.readLongLE();
		{
			int length = buffer.readIntLE();
			if (length == 0) {
				this.code = "";
			} else {
				byte[] bytes = new byte[length];
				buffer.readBytes(bytes);
				this.code = new String(bytes, StandardCharsets.UTF_16LE);
			}
		}
		this.upgrader = buffer.readLongLE();
		{
			int length = buffer.readIntLE();
			if (length == 0) {
				this.upgradercode = "";
			} else {
				byte[] bytes = new byte[length];
				buffer.readBytes(bytes);
				this.upgradercode = new String(bytes, StandardCharsets.UTF_16LE);
			}
		}
		this.prop = buffer.readIntLE();
	}
}