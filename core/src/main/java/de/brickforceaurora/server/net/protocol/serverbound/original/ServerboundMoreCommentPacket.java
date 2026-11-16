package de.brickforceaurora.server.net.protocol.serverbound.original;

import de.brickforceaurora.server.net.protocol.IServerboundPacket;
import de.brickforceaurora.server.net.protocol.PacketBuf;

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
    public void read(final PacketBuf buf) {
        this.mapSeq = buf.readInt();
        this.lastComment = buf.readInt();
    }
}