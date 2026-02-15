package de.brickforceaurora.server.net.protocol.serverbound.original;

import de.brickforceaurora.server.net.protocol.IServerboundPacket;
import de.brickforceaurora.server.net.protocol.PacketBuf;

public final class ServerboundMasterKickingPacket implements IServerboundPacket {

	private int countdown;

	public final ServerboundMasterKickingPacket countdown(int countdown) {
		this.countdown = countdown;
		return this;
	}

	public final int countdown() {
		return this.countdown;
	}

	@Override
	public int packetId() {
		return 534;
	}

	@Override
	public final void read(PacketBuf buf) {
		this.countdown = buf.readInt();
	}
}