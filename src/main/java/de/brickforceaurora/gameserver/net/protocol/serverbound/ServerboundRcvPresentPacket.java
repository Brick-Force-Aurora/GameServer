package de.brickforceaurora.gameserver.net.protocol.serverbound;

import java.nio.charset.StandardCharsets;

import de.brickforceaurora.gameserver.net.protocol.IServerboundPacket;
import io.netty.buffer.ByteBuf;

public final class ServerboundRcvPresentPacket implements IServerboundPacket {

    private long memoSeq;
    private String attached;
    private int option;
    private int val;

    public ServerboundRcvPresentPacket memoSeq(final long memoSeq) {
        this.memoSeq = memoSeq;
        return this;
    }

    public long memoSeq() {
        return this.memoSeq;
    }

    public ServerboundRcvPresentPacket attached(final String attached) {
        this.attached = attached;
        return this;
    }

    public String attached() {
        return this.attached;
    }

    public ServerboundRcvPresentPacket option(final int option) {
        this.option = option;
        return this;
    }

    public int option() {
        return this.option;
    }

    public ServerboundRcvPresentPacket val(final int val) {
        if (val > 255L || val < 0L) {
            throw new IllegalArgumentException("Value " + val + " is out of bounds of allowed number range of 0 - 255");
        }
        this.val = val;
        return this;
    }

    public int val() {
        return this.val;
    }

    @Override
    public int packetId() {
        return 132;
    }

    @Override
    public void read(final ByteBuf buffer) {
        this.memoSeq = buffer.readLongLE();
        {
            final int length = buffer.readIntLE();
            if (length == 0) {
                this.attached = "";
            } else {
                final byte[] bytes = new byte[length];
                buffer.readBytes(bytes);
                this.attached = new String(bytes, StandardCharsets.UTF_16LE);
            }
        }
        this.option = buffer.readIntLE();
        this.val = buffer.readUnsignedByte();
    }
}