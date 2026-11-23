package de.brickforceaurora.server.net.tcp.protocol.serverbound.original;

import de.brickforceaurora.server.net.tcp.protocol.IServerboundPacket;
import de.brickforceaurora.server.net.tcp.protocol.PacketBuf;

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
    public void read(final PacketBuf buf) {
        this.memoSeq = buf.readLong();
        this.attached = buf.readString();
        this.option = buf.readInt();
        this.val = buf.readUnsignedByte();
    }
}