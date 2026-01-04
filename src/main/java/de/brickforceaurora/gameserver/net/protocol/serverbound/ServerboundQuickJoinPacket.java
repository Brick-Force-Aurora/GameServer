package de.brickforceaurora.gameserver.net.protocol.serverbound;

import de.brickforceaurora.gameserver.net.protocol.IServerboundPacket;
import io.netty.buffer.ByteBuf;

public final class ServerboundQuickJoinPacket implements IServerboundPacket {

	private int qjModeMask;
	private int qjOfficialMask;

	public final ServerboundQuickJoinPacket qjModeMask(int qjModeMask) {
		this.qjModeMask = qjModeMask;
		return this;
	}

	public final int qjModeMask() {
		return this.qjModeMask;
	}

	public final ServerboundQuickJoinPacket qjOfficialMask(int qjOfficialMask) {
		this.qjOfficialMask = qjOfficialMask;
		return this;
	}

	public final int qjOfficialMask() {
		return this.qjOfficialMask;
	}

	@Override
	public int packetId() {
		return 9;
	}

	@Override
	public final void read(ByteBuf buffer) {
		this.qjModeMask = buffer.readIntLE();
		this.qjOfficialMask = buffer.readIntLE();
	}
}