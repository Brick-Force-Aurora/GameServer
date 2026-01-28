package de.brickforceaurora.gameserver.net.protocol.serverbound;

import de.brickforceaurora.gameserver.net.protocol.IServerboundPacket;
import io.netty.buffer.ByteBuf;

public final class ServerboundMoreCommentPacket implements IServerboundPacket {

    private int mapSeq;
    private int lastComment;

    public ServerboundMoreCommentPacket mapSeq(final int mapSeq) {
        this.mapSeq = mapSeq;
        return this;
    }

    public int mapSeq() {
        return this.mapSeq;
    }

    public ServerboundMoreCommentPacket lastComment(final int lastComment) {
        this.lastComment = lastComment;
        return this;
    }

    public int lastComment() {
        return this.lastComment;
    }

    @Override
    public int packetId() {
        return 439;
    }

    @Override
    public void read(final ByteBuf buffer) {
        this.mapSeq = buffer.readIntLE();
        this.lastComment = buffer.readIntLE();
    }
}