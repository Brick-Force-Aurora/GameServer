package de.brickforceaurora.server.net.protocol.clientbound.original;

import de.brickforceaurora.server.net.protocol.IClientboundPacket;
import de.brickforceaurora.server.net.protocol.PacketBuf;

public final class ClientboundKickoutVoteEndPacket implements IClientboundPacket {

	private int result;
	private int target;
	private String targetNickname;

	public final ClientboundKickoutVoteEndPacket result(int result) {
		this.result = result;
		return this;
	}

	public final int result() {
		return this.result;
	}

	public final ClientboundKickoutVoteEndPacket target(int target) {
		this.target = target;
		return this;
	}

	public final int target() {
		return this.target;
	}

	public final ClientboundKickoutVoteEndPacket targetNickname(String targetNickname) {
		this.targetNickname = targetNickname;
		return this;
	}

	public final String targetNickname() {
		return this.targetNickname;
	}

	@Override
	public int packetId() {
		return 499;
	}

	@Override
	public final void write(PacketBuf buf) {
		buf.writeInt(this.result);
		buf.writeInt(this.target);
		buf.writeString(this.targetNickname);
	}
}
