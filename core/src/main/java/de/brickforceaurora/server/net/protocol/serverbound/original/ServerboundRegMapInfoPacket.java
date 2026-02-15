package de.brickforceaurora.server.net.protocol.serverbound.original;

import de.brickforceaurora.server.net.protocol.IServerboundPacket;
import de.brickforceaurora.server.net.protocol.PacketBuf;

public final class ServerboundRegMapInfoPacket implements IServerboundPacket {

	private int map;

	public final ServerboundRegMapInfoPacket map(int map) {
		this.map = map;
		return this;
	}

	public final int map() {
		return this.map;
	}

	@Override
	public int packetId() {
		return 337;
	}

	@Override
	public final void read(PacketBuf buf) {
		this.map = buf.readInt();
	}
}