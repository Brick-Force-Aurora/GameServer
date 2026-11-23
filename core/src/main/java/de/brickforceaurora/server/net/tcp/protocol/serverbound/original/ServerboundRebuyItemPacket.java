package de.brickforceaurora.server.net.tcp.protocol.serverbound.original;

import de.brickforceaurora.server.net.tcp.protocol.IServerboundPacket;
import de.brickforceaurora.server.net.tcp.protocol.PacketBuf;

public final class ServerboundRebuyItemPacket implements IServerboundPacket {

    private long itemSeq;
    private String code;
    private int buyHow;
    private int option;
    private int val;
    private boolean needEqup;

    public ServerboundRebuyItemPacket itemSeq(final long itemSeq) {
        this.itemSeq = itemSeq;
        return this;
    }

    public long itemSeq() {
        return this.itemSeq;
    }

    public ServerboundRebuyItemPacket code(final String code) {
        this.code = code;
        return this;
    }

    public String code() {
        return this.code;
    }

    public ServerboundRebuyItemPacket buyHow(final int buyHow) {
        this.buyHow = buyHow;
        return this;
    }

    public int buyHow() {
        return this.buyHow;
    }

    public ServerboundRebuyItemPacket option(final int option) {
        this.option = option;
        return this;
    }

    public int option() {
        return this.option;
    }

    public ServerboundRebuyItemPacket val(final int val) {
        if (val > 255L || val < 0L) {
            throw new IllegalArgumentException("Value " + val + " is out of bounds of allowed number range of 0 - 255");
        }
        this.val = val;
        return this;
    }

    public int val() {
        return this.val;
    }

    public ServerboundRebuyItemPacket needEqup(final boolean needEqup) {
        this.needEqup = needEqup;
        return this;
    }

    public boolean needEqup() {
        return this.needEqup;
    }

    @Override
    public int packetId() {
        return 309;
    }

    @Override
    public void read(final PacketBuf buf) {
        this.itemSeq = buf.readLong();
        this.code = buf.readString();
        this.buyHow = buf.readInt();
        this.option = buf.readInt();
        this.val = buf.readUnsignedByte();
        this.needEqup = buf.readBoolean();
    }
}