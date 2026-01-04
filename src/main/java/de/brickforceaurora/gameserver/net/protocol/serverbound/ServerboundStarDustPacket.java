package de.brickforceaurora.gameserver.net.protocol.serverbound;

import de.brickforceaurora.gameserver.net.protocol.IServerboundPacket;
import io.netty.buffer.ByteBuf;

public final class ServerboundStarDustPacket implements IServerboundPacket {

	private int map;
	private int starDust;

	public final ServerboundStarDustPacket map(int map) {
		this.map = map;
		return this;
	}

	public final int map() {
		return this.map;
	}

	public final ServerboundStarDustPacket starDust(int starDust) {
		this.starDust = starDust;
		return this;
	}

	public final int starDust() {
		return this.starDust;
	}

	@Override
	public int packetId() {
		return 60;
	}

	@Override
	public final void read(ByteBuf buffer) {
		this.map = buffer.readIntLE();
		this.starDust = buffer.readIntLE();
	}
}