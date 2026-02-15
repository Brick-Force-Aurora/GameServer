package de.brickforceaurora.server.net.protocol.serverbound.original;

import de.brickforceaurora.server.net.protocol.IServerboundPacket;
import de.brickforceaurora.server.net.protocol.PacketBuf;

public final class ServerboundOpenRandomBoxPacket implements IServerboundPacket {

	private int randombox;

	public final ServerboundOpenRandomBoxPacket randombox(int randombox) {
		this.randombox = randombox;
		return this;
	}

	public final int randombox() {
		return this.randombox;
	}

	@Override
	public int packetId() {
		return 219;
	}

	@Override
	public final void read(PacketBuf buf) {
		this.randombox = buf.readInt();
	}
}