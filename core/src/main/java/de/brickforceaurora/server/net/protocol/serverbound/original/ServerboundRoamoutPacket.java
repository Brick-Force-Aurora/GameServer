package de.brickforceaurora.server.net.protocol.serverbound.original;

import de.brickforceaurora.server.net.protocol.IServerboundPacket;
import de.brickforceaurora.server.net.protocol.PacketBuf;

public final class ServerboundRoamoutPacket implements IServerboundPacket {

	private int dst;

	public final ServerboundRoamoutPacket dst(int dst) {
		this.dst = dst;
		return this;
	}

	public final int dst() {
		return this.dst;
	}

	@Override
	public int packetId() {
		return 143;
	}

	@Override
	public final void read(PacketBuf buf) {
		this.dst = buf.readInt();
	}
}