package de.brickforceaurora.server.net.protocol.serverbound.original;

import de.brickforceaurora.server.net.protocol.IServerboundPacket;
import de.brickforceaurora.server.net.protocol.PacketBuf;

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
	public final void read(PacketBuf buf) {
		this.map = buf.readInt();
		this.starDust = buf.readInt();
	}
}