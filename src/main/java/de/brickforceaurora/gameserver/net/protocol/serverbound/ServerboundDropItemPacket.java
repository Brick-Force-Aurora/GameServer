package de.brickforceaurora.gameserver.net.protocol.serverbound;

import de.brickforceaurora.gameserver.net.protocol.IServerboundPacket;
import io.netty.buffer.ByteBuf;
import java.nio.charset.StandardCharsets;

public final class ServerboundDropItemPacket implements IServerboundPacket {

	private String itemCode;
	private int count;
	private int subCount;
	private float x;
	private float y;
	private float z;

	public final ServerboundDropItemPacket itemCode(String itemCode) {
		this.itemCode = itemCode;
		return this;
	}

	public final String itemCode() {
		return this.itemCode;
	}

	public final ServerboundDropItemPacket count(int count) {
		this.count = count;
		return this;
	}

	public final int count() {
		return this.count;
	}

	public final ServerboundDropItemPacket subCount(int subCount) {
		this.subCount = subCount;
		return this;
	}

	public final int subCount() {
		return this.subCount;
	}

	public final ServerboundDropItemPacket x(float x) {
		this.x = x;
		return this;
	}

	public final float x() {
		return this.x;
	}

	public final ServerboundDropItemPacket y(float y) {
		this.y = y;
		return this;
	}

	public final float y() {
		return this.y;
	}

	public final ServerboundDropItemPacket z(float z) {
		this.z = z;
		return this;
	}

	public final float z() {
		return this.z;
	}

	@Override
	public int packetId() {
		return 526;
	}

	@Override
	public final void read(ByteBuf buffer) {
		{
			int length = buffer.readIntLE();
			if (length == 0) {
				this.itemCode = "";
			} else {
				byte[] bytes = new byte[length];
				buffer.readBytes(bytes);
				this.itemCode = new String(bytes, StandardCharsets.UTF_16LE);
			}
		}
		this.count = buffer.readIntLE();
		this.subCount = buffer.readIntLE();
		this.x = buffer.readFloatLE();
		this.y = buffer.readFloatLE();
		this.z = buffer.readFloatLE();
	}
}