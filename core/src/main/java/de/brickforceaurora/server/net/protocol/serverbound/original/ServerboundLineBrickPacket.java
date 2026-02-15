package de.brickforceaurora.server.net.protocol.serverbound.original;

import de.brickforceaurora.server.net.protocol.IServerboundPacket;
import de.brickforceaurora.server.net.protocol.PacketBuf;

public final class ServerboundLineBrickPacket implements IServerboundPacket {

	private long item;
	private String code;
	private int brick;
	private int x;
	private int y;
	private int z;
	private int rot;

	public final ServerboundLineBrickPacket item(long item) {
		this.item = item;
		return this;
	}

	public final long item() {
		return this.item;
	}

	public final ServerboundLineBrickPacket code(String code) {
		this.code = code;
		return this;
	}

	public final String code() {
		return this.code;
	}

	public final ServerboundLineBrickPacket brick(int brick) {
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

	public final ServerboundLineBrickPacket x(int x) {
		if (x > 255L || x < 0L) {
			throw new IllegalArgumentException("Value " + x + " is out of bounds of allowed number range of 0 - 255");
		}
		this.x = x;
		return this;
	}

	public final int x() {
		return this.x;
	}

	public final ServerboundLineBrickPacket y(int y) {
		if (y > 255L || y < 0L) {
			throw new IllegalArgumentException("Value " + y + " is out of bounds of allowed number range of 0 - 255");
		}
		this.y = y;
		return this;
	}

	public final int y() {
		return this.y;
	}

	public final ServerboundLineBrickPacket z(int z) {
		if (z > 255L || z < 0L) {
			throw new IllegalArgumentException("Value " + z + " is out of bounds of allowed number range of 0 - 255");
		}
		this.z = z;
		return this;
	}

	public final int z() {
		return this.z;
	}

	public final ServerboundLineBrickPacket rot(int rot) {
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
		return 325;
	}

	@Override
	public final void read(PacketBuf buf) {
		this.item = buf.readLong();
		this.code = buf.readString();
		this.brick = buf.readUnsignedByte();
		this.x = buf.readUnsignedByte();
		this.y = buf.readUnsignedByte();
		this.z = buf.readUnsignedByte();
		this.rot = buf.readUnsignedByte();
	}
}