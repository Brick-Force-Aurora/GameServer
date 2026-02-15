package de.brickforceaurora.server.net.protocol.serverbound.original;

import de.brickforceaurora.server.net.protocol.IServerboundPacket;
import de.brickforceaurora.server.net.protocol.PacketBuf;

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
	public final void read(PacketBuf buf) {
		this.itemCode = buf.readString();
		this.count = buf.readInt();
		this.subCount = buf.readInt();
		this.x = buf.readFloat();
		this.y = buf.readFloat();
		this.z = buf.readFloat();
	}
}