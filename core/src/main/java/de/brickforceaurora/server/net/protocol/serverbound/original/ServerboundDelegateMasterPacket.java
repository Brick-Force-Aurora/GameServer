package de.brickforceaurora.server.net.protocol.serverbound.original;

import de.brickforceaurora.server.net.protocol.IServerboundPacket;
import de.brickforceaurora.server.net.protocol.PacketBuf;

public final class ServerboundDelegateMasterPacket implements IServerboundPacket {

	private int newMater;

	public final ServerboundDelegateMasterPacket newMater(int newMater) {
		this.newMater = newMater;
		return this;
	}

	public final int newMater() {
		return this.newMater;
	}

	@Override
	public int packetId() {
		return 389;
	}

	@Override
	public final void read(PacketBuf buf) {
		this.newMater = buf.readInt();
	}
}