package de.brickforceaurora.server.net.protocol.clientbound.original;

import de.brickforceaurora.server.net.protocol.IClientboundPacket;
import de.brickforceaurora.server.net.protocol.PacketBuf;

public final class ClientboundKickoutVoteStatusPacket implements IClientboundPacket {

	private int yes;
	private int no;
	private int total;
	private int reason;
	private int target;
	private String targetNickname;
	private boolean isVoteAble;
	private boolean isVoted;
	private int remainTime;

	public final ClientboundKickoutVoteStatusPacket yes(int yes) {
		this.yes = yes;
		return this;
	}

	public final int yes() {
		return this.yes;
	}

	public final ClientboundKickoutVoteStatusPacket no(int no) {
		this.no = no;
		return this;
	}

	public final int no() {
		return this.no;
	}

	public final ClientboundKickoutVoteStatusPacket total(int total) {
		this.total = total;
		return this;
	}

	public final int total() {
		return this.total;
	}

	public final ClientboundKickoutVoteStatusPacket reason(int reason) {
		this.reason = reason;
		return this;
	}

	public final int reason() {
		return this.reason;
	}

	public final ClientboundKickoutVoteStatusPacket target(int target) {
		this.target = target;
		return this;
	}

	public final int target() {
		return this.target;
	}

	public final ClientboundKickoutVoteStatusPacket targetNickname(String targetNickname) {
		this.targetNickname = targetNickname;
		return this;
	}

	public final String targetNickname() {
		return this.targetNickname;
	}

	public final ClientboundKickoutVoteStatusPacket isVoteAble(boolean isVoteAble) {
		this.isVoteAble = isVoteAble;
		return this;
	}

	public final boolean isVoteAble() {
		return this.isVoteAble;
	}

	public final ClientboundKickoutVoteStatusPacket isVoted(boolean isVoted) {
		this.isVoted = isVoted;
		return this;
	}

	public final boolean isVoted() {
		return this.isVoted;
	}

	public final ClientboundKickoutVoteStatusPacket remainTime(int remainTime) {
		this.remainTime = remainTime;
		return this;
	}

	public final int remainTime() {
		return this.remainTime;
	}

	@Override
	public int packetId() {
		return 498;
	}

	@Override
	public final void write(PacketBuf buf) {
		buf.writeInt(this.yes);
		buf.writeInt(this.no);
		buf.writeInt(this.total);
		buf.writeInt(this.reason);
		buf.writeInt(this.target);
		buf.writeString(this.targetNickname);
		buf.writeBoolean(this.isVoteAble);
		buf.writeBoolean(this.isVoted);
		buf.writeInt(this.remainTime);
	}
}