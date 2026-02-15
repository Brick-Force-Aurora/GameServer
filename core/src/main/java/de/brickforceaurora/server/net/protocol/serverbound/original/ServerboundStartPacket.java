package de.brickforceaurora.server.net.protocol.serverbound.original;

import de.brickforceaurora.server.net.protocol.IServerboundPacket;
import de.brickforceaurora.server.net.protocol.PacketBuf;

public final class ServerboundStartPacket implements IServerboundPacket {

	private int remain;

	public final ServerboundStartPacket remain(int remain) {
		this.remain = remain;
		return this;
	}

	public final int remain() {
		return this.remain;
	}

	@Override
	public int packetId() {
		return 49;
	}

	@Override
	public final void read(PacketBuf buf) {
		this.remain = buf.readInt();
	}
}