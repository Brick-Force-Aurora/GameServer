package de.brickforceaurora.gameserver.net.protocol.serverbound;

import de.brickforceaurora.gameserver.net.protocol.IServerboundPacket;
import io.netty.buffer.ByteBuf;

public final class ServerboundMoreCommentPacket implements IServerboundPacket {

	private int mapSeq;
	private int lastComment;

	public final ServerboundMoreCommentPacket mapSeq(int mapSeq) {
		this.mapSeq = mapSeq;
		return this;
	}

	public final int mapSeq() {
		return this.mapSeq;
	}

	public final ServerboundMoreCommentPacket lastComment(int lastComment) {
		this.lastComment = lastComment;
		return this;
	}

	public final int lastComment() {
		return this.lastComment;
	}

	@Override
	public int packetId() {
		return 439;
	}

	@Override
	public final void read(ByteBuf buffer) {
		this.mapSeq = buffer.readIntLE();
		this.lastComment = buffer.readIntLE();
	}
}