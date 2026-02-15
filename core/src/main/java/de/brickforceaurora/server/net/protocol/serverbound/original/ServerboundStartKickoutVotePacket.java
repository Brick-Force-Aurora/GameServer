package de.brickforceaurora.server.net.protocol.serverbound.original;

import de.brickforceaurora.server.net.protocol.IServerboundPacket;
import de.brickforceaurora.server.net.protocol.PacketBuf;

public final class ServerboundStartKickoutVotePacket implements IServerboundPacket {

	private int target;
	private int reason;

	public final ServerboundStartKickoutVotePacket target(int target) {
		this.target = target;
		return this;
	}

	public final int target() {
		return this.target;
	}

	public final ServerboundStartKickoutVotePacket reason(int reason) {
		this.reason = reason;
		return this;
	}

	public final int reason() {
		return this.reason;
	}

	@Override
	public int packetId() {
		return 494;
	}

	@Override
	public final void read(PacketBuf buf) {
		this.target = buf.readInt();
		this.reason = buf.readInt();
	}
}