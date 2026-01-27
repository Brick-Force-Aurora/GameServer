package de.brickforceaurora.gameserver.net.protocol.serverbound;

import de.brickforceaurora.gameserver.net.protocol.IServerboundPacket;
import io.netty.buffer.ByteBuf;

public final class ServerboundCheckTermItemPacket implements IServerboundPacket {

	private long expiring;
	private long alter;

	public final ServerboundCheckTermItemPacket expiring(long expiring) {
		this.expiring = expiring;
		return this;
	}

	public final long expiring() {
		return this.expiring;
	}

	public final ServerboundCheckTermItemPacket alter(long alter) {
		this.alter = alter;
		return this;
	}

	public final long alter() {
		return this.alter;
	}

	@Override
	public int packetId() {
		return 123;
	}

	@Override
	public final void read(ByteBuf buffer) {
		this.expiring = buffer.readLongLE();
		this.alter = buffer.readLongLE();
	}
}