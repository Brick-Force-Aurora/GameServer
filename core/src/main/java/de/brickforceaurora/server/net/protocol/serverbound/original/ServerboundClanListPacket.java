package de.brickforceaurora.server.net.protocol.serverbound.original;

import de.brickforceaurora.server.net.protocol.IServerboundPacket;
import de.brickforceaurora.server.net.protocol.PacketBuf;

public final class ServerboundClanListPacket implements IServerboundPacket {

    private int prev;
    private int next;
    private int index;
    private String keyword;

    public ServerboundClanListPacket prev(final int prev) {
        this.prev = prev;
        return this;
    }

    public int prev() {
        return this.prev;
    }

    public ServerboundClanListPacket next(final int next) {
        this.next = next;
        return this;
    }

    public int next() {
        return this.next;
    }

    public ServerboundClanListPacket index(final int index) {
        this.index = index;
        return this;
    }

    public int index() {
        return this.index;
    }

    public ServerboundClanListPacket keyword(final String keyword) {
        this.keyword = keyword;
        return this;
    }

    public String keyword() {
        return this.keyword;
    }

    @Override
    public int packetId() {
        return 297;
    }

    @Override
    public void read(final PacketBuf buf) {
        this.prev = buf.readInt();
        this.next = buf.readInt();
        this.index = buf.readInt();
        this.keyword = buf.readString();
    }
}