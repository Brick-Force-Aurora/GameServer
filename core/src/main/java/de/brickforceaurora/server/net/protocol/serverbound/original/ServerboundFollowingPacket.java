package de.brickforceaurora.server.net.protocol.serverbound.original;

import de.brickforceaurora.server.net.protocol.IServerboundPacket;
import de.brickforceaurora.server.net.protocol.PacketBuf;

public final class ServerboundFollowingPacket implements IServerboundPacket {

	private int followeeSeq;
	private String nickName;

	public final ServerboundFollowingPacket followeeSeq(int followeeSeq) {
		this.followeeSeq = followeeSeq;
		return this;
	}

	public final int followeeSeq() {
		return this.followeeSeq;
	}

	public final ServerboundFollowingPacket nickName(String nickName) {
		this.nickName = nickName;
		return this;
	}

	public final String nickName() {
		return this.nickName;
	}

	@Override
	public int packetId() {
		return 484;
	}

	@Override
	public final void read(PacketBuf buf) {
		this.followeeSeq = buf.readInt();
		this.nickName = buf.readString();
	}
}