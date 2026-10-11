package de.brickforceaurora.server.net.protocol.serverbound.original;

import de.brickforceaurora.server.net.protocol.IServerboundPacket;
import de.brickforceaurora.server.net.protocol.PacketBuf;

public final class ServerboundDelegateMasterPacket implements IServerboundPacket {

	private int newMaster;

	public final ServerboundDelegateMasterPacket newMaster(int newMaster) {
		this.newMaster = newMaster;
		return this;
	}

	public final int newMaster() {
		return this.newMaster;
	}

	@Override
	public int packetId() {
		return 389;
	}

	@Override
	public final void read(PacketBuf buf) {
		this.newMaster = buf.readInt();
	}
}