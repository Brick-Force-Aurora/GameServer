package de.brickforceaurora.gameserver.net.protocol.clientbound;

import de.brickforceaurora.gameserver.net.protocol.IClientboundPacket;
import io.netty.buffer.ByteBuf;
import java.nio.charset.StandardCharsets;

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
	public final void write(ByteBuf buffer) {
		buffer.writeIntLE(this.yes);
		buffer.writeIntLE(this.no);
		buffer.writeIntLE(this.total);
		buffer.writeIntLE(this.reason);
		buffer.writeIntLE(this.target);
		if (this.targetNickname.isEmpty()) {
			buffer.writeIntLE(0);
		} else {
			byte[] bytes = this.targetNickname.getBytes(StandardCharsets.UTF_16LE);
			buffer.writeIntLE(bytes.length);
			buffer.writeBytes(bytes);
		}
		buffer.writeBoolean(this.isVoteAble);
		buffer.writeBoolean(this.isVoted);
		buffer.writeIntLE(this.remainTime);
	}
}