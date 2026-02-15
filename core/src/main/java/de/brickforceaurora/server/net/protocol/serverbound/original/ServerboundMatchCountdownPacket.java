package de.brickforceaurora.server.net.protocol.serverbound.original;

import de.brickforceaurora.server.net.protocol.IServerboundPacket;
import de.brickforceaurora.server.net.protocol.PacketBuf;

public final class ServerboundMatchCountdownPacket implements IServerboundPacket {

	private int count;

	public final ServerboundMatchCountdownPacket count(int count) {
		this.count = count;
		return this;
	}

	public final int count() {
		return this.count;
	}

	@Override
	public int packetId() {
		return 71;
	}

	@Override
	public final void read(PacketBuf buf) {
		this.count = buf.readInt();
	}
}