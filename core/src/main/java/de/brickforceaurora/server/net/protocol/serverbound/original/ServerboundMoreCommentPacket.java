package de.brickforceaurora.server.net.protocol.serverbound.original;

import de.brickforceaurora.server.net.protocol.IServerboundPacket;
import de.brickforceaurora.server.net.protocol.PacketBuf;

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
	public final void read(PacketBuf buf) {
		this.mapSeq = buf.readInt();
		this.lastComment = buf.readInt();
	}
}