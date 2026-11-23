package de.brickforceaurora.server.net.tcp.protocol.serverbound.original;

import de.brickforceaurora.server.net.tcp.protocol.IServerboundPacket;
import de.brickforceaurora.server.net.tcp.protocol.PacketBuf;

public final class ServerboundMergeItemPacket implements IServerboundPacket {

    private long src;
    private long dst;
    private String code;

    public ServerboundMergeItemPacket src(final long src) {
        this.src = src;
        return this;
    }

    public long src() {
        return this.src;
    }

    public ServerboundMergeItemPacket dst(final long dst) {
        this.dst = dst;
        return this;
    }

    public long dst() {
        return this.dst;
    }

    public ServerboundMergeItemPacket code(final String code) {
        this.code = code;
        return this;
    }

    public String code() {
        return this.code;
    }

    @Override
    public int packetId() {
        return 357;
    }

    @Override
    public void read(final PacketBuf buf) {
        this.src = buf.readLong();
        this.dst = buf.readLong();
        this.code = buf.readString();
    }
}