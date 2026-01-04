package de.brickforceaurora.gameserver.net.protocol.serverbound;

import de.brickforceaurora.gameserver.net.protocol.IServerboundPacket;
import io.netty.buffer.ByteBuf;

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
	public final void read(ByteBuf buffer) {
		this.newMater = buffer.readIntLE();
	}
}