package de.brickforceaurora.gameserver.net.protocol.serverbound;

import de.brickforceaurora.gameserver.net.protocol.IServerboundPacket;
import io.netty.buffer.ByteBuf;

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
	public final void read(ByteBuf buffer) {
		this.target = buffer.readIntLE();
		this.reason = buffer.readIntLE();
	}
}