package de.brickforceaurora.gameserver.net.protocol.serverbound;

import de.brickforceaurora.gameserver.net.protocol.IServerboundPacket;
import io.netty.buffer.ByteBuf;

public final class ServerboundCoreHpPacket implements IServerboundPacket {

	private int redHp;
	private int blueHp;

	public final ServerboundCoreHpPacket redHp(int redHp) {
		this.redHp = redHp;
		return this;
	}

	public final int redHp() {
		return this.redHp;
	}

	public final ServerboundCoreHpPacket blueHp(int blueHp) {
		this.blueHp = blueHp;
		return this;
	}

	public final int blueHp() {
		return this.blueHp;
	}

	@Override
	public int packetId() {
		return 342;
	}

	@Override
	public final void read(ByteBuf buffer) {
		this.redHp = buffer.readIntLE();
		this.blueHp = buffer.readIntLE();
	}
}