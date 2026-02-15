package de.brickforceaurora.server.net.protocol.serverbound.original;

import de.brickforceaurora.server.net.protocol.IServerboundPacket;
import de.brickforceaurora.server.net.protocol.PacketBuf;

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
	public final void read(PacketBuf buf) {
		this.qjModeMask = buf.readInt();
		this.qjOfficialMask = buf.readInt();
	}
}