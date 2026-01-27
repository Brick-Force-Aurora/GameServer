package de.brickforceaurora.gameserver.net.protocol.serverbound;

import de.brickforceaurora.gameserver.net.protocol.IServerboundPacket;
import io.netty.buffer.ByteBuf;
import java.nio.charset.StandardCharsets;

public final class ServerboundReplaceBrickPacket implements IServerboundPacket {

	private long item;
	private String code;
	private int existing;
	private int brick;
	private int x;
	private int y;
	private int z;
	private int rot;

	public final ServerboundReplaceBrickPacket item(long item) {
		this.item = item;
		return this;
	}

	public final long item() {
		return this.item;
	}

	public final ServerboundReplaceBrickPacket code(String code) {
		this.code = code;
		return this;
	}

	public final String code() {
		return this.code;
	}

	public final ServerboundReplaceBrickPacket existing(int existing) {
		this.existing = existing;
		return this;
	}

	public final int existing() {
		return this.existing;
	}

	public final ServerboundReplaceBrickPacket brick(int brick) {
		if (brick > 255L || brick < 0L) {
			throw new IllegalArgumentException(
					"Value " + brick + " is out of bounds of allowed number range of 0 - 255");
		}
		this.brick = brick;
		return this;
	}

	public final int brick() {
		return this.brick;
	}

	public final ServerboundReplaceBrickPacket x(int x) {
		if (x > 255L || x < 0L) {
			throw new IllegalArgumentException("Value " + x + " is out of bounds of allowed number range of 0 - 255");
		}
		this.x = x;
		return this;
	}

	public final int x() {
		return this.x;
	}

	public final ServerboundReplaceBrickPacket y(int y) {
		if (y > 255L || y < 0L) {
			throw new IllegalArgumentException("Value " + y + " is out of bounds of allowed number range of 0 - 255");
		}
		this.y = y;
		return this;
	}

	public final int y() {
		return this.y;
	}

	public final ServerboundReplaceBrickPacket z(int z) {
		if (z > 255L || z < 0L) {
			throw new IllegalArgumentException("Value " + z + " is out of bounds of allowed number range of 0 - 255");
		}
		this.z = z;
		return this;
	}

	public final int z() {
		return this.z;
	}

	public final ServerboundReplaceBrickPacket rot(int rot) {
		if (rot > 255L || rot < 0L) {
			throw new IllegalArgumentException("Value " + rot + " is out of bounds of allowed number range of 0 - 255");
		}
		this.rot = rot;
		return this;
	}

	public final int rot() {
		return this.rot;
	}

	@Override
	public int packetId() {
		return 327;
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
		this.existing = buffer.readIntLE();
		this.brick = buffer.readUnsignedByte();
		this.x = buffer.readUnsignedByte();
		this.y = buffer.readUnsignedByte();
		this.z = buffer.readUnsignedByte();
		this.rot = buffer.readUnsignedByte();
	}
}