package de.brickforceaurora.server.net.protocol.serverbound.original;

import de.brickforceaurora.server.net.protocol.IServerboundPacket;
import de.brickforceaurora.server.net.protocol.PacketBuf;

public final class ServerboundInvitePacket implements IServerboundPacket {

	private int inviteeSeq;
	private String nickName;

	public final ServerboundInvitePacket inviteeSeq(int inviteeSeq) {
		this.inviteeSeq = inviteeSeq;
		return this;
	}

	public final int inviteeSeq() {
		return this.inviteeSeq;
	}

	public final ServerboundInvitePacket nickName(String nickName) {
		this.nickName = nickName;
		return this;
	}

	public final String nickName() {
		return this.nickName;
	}

	@Override
	public int packetId() {
		return 481;
	}

	@Override
	public final void read(PacketBuf buf) {
		this.inviteeSeq = buf.readInt();
		this.nickName = buf.readString();
	}
}