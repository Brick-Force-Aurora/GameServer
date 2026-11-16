package de.brickforceaurora.server.net.protocol.serverbound.original;

import de.brickforceaurora.server.net.protocol.IServerboundPacket;
import de.brickforceaurora.server.net.protocol.PacketBuf;

public final class ServerboundMapEvalPacket implements IServerboundPacket {

    private int map;
    private int likesOrDislikes;
    private String comment;

    public ServerboundMapEvalPacket map(final int map) {
        this.map = map;
        return this;
    }

    public int map() {
        return this.map;
    }

    public ServerboundMapEvalPacket likesOrDislikes(final int likesOrDislikes) {
        if (likesOrDislikes > 255L || likesOrDislikes < 0L) {
            throw new IllegalArgumentException("Value " + likesOrDislikes + " is out of bounds of allowed number range of 0 - 255");
        }
        this.likesOrDislikes = likesOrDislikes;
        return this;
    }

    public int likesOrDislikes() {
        return this.likesOrDislikes;
    }

    public ServerboundMapEvalPacket comment(final String comment) {
        this.comment = comment;
        return this;
    }

    public String comment() {
        return this.comment;
    }

    @Override
    public int packetId() {
        return 423;
    }

    @Override
    public void read(final PacketBuf buf) {
        this.map = buf.readInt();
        this.likesOrDislikes = buf.readUnsignedByte();
        this.comment = buf.readString();
    }
}