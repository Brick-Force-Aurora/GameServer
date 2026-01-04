package de.brickforceaurora.gameserver.net.protocol.serverbound;

import de.brickforceaurora.gameserver.net.protocol.IServerboundPacket;
import io.netty.buffer.ByteBuf;

public final class ServerboundBmInstallBombPacket implements IServerboundPacket {

	private int bomb;
	private float posX;
	private float posY;
	private float posZ;
	private float normalX;
	private float normalY;
	private float normalZ;

	public final ServerboundBmInstallBombPacket bomb(int bomb) {
		this.bomb = bomb;
		return this;
	}

	public final int bomb() {
		return this.bomb;
	}

	public final ServerboundBmInstallBombPacket posX(float posX) {
		this.posX = posX;
		return this;
	}

	public final float posX() {
		return this.posX;
	}

	public final ServerboundBmInstallBombPacket posY(float posY) {
		this.posY = posY;
		return this;
	}

	public final float posY() {
		return this.posY;
	}

	public final ServerboundBmInstallBombPacket posZ(float posZ) {
		this.posZ = posZ;
		return this;
	}

	public final float posZ() {
		return this.posZ;
	}

	public final ServerboundBmInstallBombPacket normalX(float normalX) {
		this.normalX = normalX;
		return this;
	}

	public final float normalX() {
		return this.normalX;
	}

	public final ServerboundBmInstallBombPacket normalY(float normalY) {
		this.normalY = normalY;
		return this;
	}

	public final float normalY() {
		return this.normalY;
	}

	public final ServerboundBmInstallBombPacket normalZ(float normalZ) {
		this.normalZ = normalZ;
		return this;
	}

	public final float normalZ() {
		return this.normalZ;
	}

	@Override
	public int packetId() {
		return 279;
	}

	@Override
	public final void read(ByteBuf buffer) {
		this.bomb = buffer.readIntLE();
		this.posX = buffer.readFloatLE();
		this.posY = buffer.readFloatLE();
		this.posZ = buffer.readFloatLE();
		this.normalX = buffer.readFloatLE();
		this.normalY = buffer.readFloatLE();
		this.normalZ = buffer.readFloatLE();
	}
}