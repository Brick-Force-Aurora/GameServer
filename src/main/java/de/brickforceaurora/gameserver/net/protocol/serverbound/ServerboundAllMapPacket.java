package de.brickforceaurora.gameserver.net.protocol.serverbound;

import java.nio.charset.StandardCharsets;

import de.brickforceaurora.gameserver.net.protocol.IServerboundPacket;
import io.netty.buffer.ByteBuf;

public final class ServerboundAllMapPacket implements IServerboundPacket {

    private int prevPage;
    private int nextPage;
    private int indexer;
    private int modeMask;
    private int flag;
    private String filter;

    public ServerboundAllMapPacket prevPage(final int prevPage) {
        this.prevPage = prevPage;
        return this;
    }

    public int prevPage() {
        return this.prevPage;
    }

    public ServerboundAllMapPacket nextPage(final int nextPage) {
        this.nextPage = nextPage;
        return this;
    }

    public int nextPage() {
        return this.nextPage;
    }

    public ServerboundAllMapPacket indexer(final int indexer) {
        this.indexer = indexer;
        return this;
    }

    public int indexer() {
        return this.indexer;
    }

    public ServerboundAllMapPacket modeMask(final int modeMask) {
        if (modeMask > 32767L || modeMask < 0L) {
            throw new IllegalArgumentException("Value " + modeMask + " is out of bounds of allowed number range of 0 - 32767");
        }
        this.modeMask = modeMask;
        return this;
    }

    public int modeMask() {
        return this.modeMask;
    }

    public ServerboundAllMapPacket flag(final int flag) {
        this.flag = flag;
        return this;
    }

    public int flag() {
        return this.flag;
    }

    public ServerboundAllMapPacket filter(final String filter) {
        this.filter = filter;
        return this;
    }

    public String filter() {
        return this.filter;
    }

    @Override
    public int packetId() {
        return 431;
    }

    @Override
    public void read(final ByteBuf buffer) {
        this.prevPage = buffer.readIntLE();
        this.nextPage = buffer.readIntLE();
        this.indexer = buffer.readIntLE();
        this.modeMask = buffer.readUnsignedShortLE();
        this.flag = buffer.readIntLE();
        {
            final int length = buffer.readIntLE();
            if (length == 0) {
                this.filter = "";
            } else {
                final byte[] bytes = new byte[length];
                buffer.readBytes(bytes);
                this.filter = new String(bytes, StandardCharsets.UTF_16LE);
            }
        }
    }
}