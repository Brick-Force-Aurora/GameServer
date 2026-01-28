package de.brickforceaurora.gameserver.net.protocol.serverbound;

import java.nio.charset.StandardCharsets;

import de.brickforceaurora.gameserver.net.protocol.IServerboundPacket;
import io.netty.buffer.ByteBuf;

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
    public void read(final ByteBuf buffer) {
        this.prev = buffer.readIntLE();
        this.next = buffer.readIntLE();
        this.index = buffer.readIntLE();
        {
            final int length = buffer.readIntLE();
            if (length == 0) {
                this.keyword = "";
            } else {
                final byte[] bytes = new byte[length];
                buffer.readBytes(bytes);
                this.keyword = new String(bytes, StandardCharsets.UTF_16LE);
            }
        }
    }
}