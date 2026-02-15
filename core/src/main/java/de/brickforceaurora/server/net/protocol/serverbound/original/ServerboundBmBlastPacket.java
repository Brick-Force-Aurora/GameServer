package de.brickforceaurora.server.net.protocol.serverbound.original;

import de.brickforceaurora.server.net.protocol.IServerboundPacket;
import de.brickforceaurora.server.net.protocol.PacketBuf;

public final class ServerboundBmBlastPacket implements IServerboundPacket {

	private int bomb;

	public final ServerboundBmBlastPacket bomb(int bomb) {
		this.bomb = bomb;
		return this;
	}

	public final int bomb() {
		return this.bomb;
	}

	@Override
	public int packetId() {
		return 283;
	}

	@Override
	public final void read(PacketBuf buf) {
		this.bomb = buf.readInt();
	}
}