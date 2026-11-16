package de.brickforceaurora.server.net.protocol.serverbound.original;

import de.brickforceaurora.server.net.protocol.IServerboundPacket;
import de.brickforceaurora.server.net.protocol.PacketBuf;

public final class ServerboundDiscomposeItemPacket implements IServerboundPacket {

    private long item;
    private String code;
    private int opt;

    public ServerboundDiscomposeItemPacket item(final long item) {
        this.item = item;
        return this;
    }

    public long item() {
        return this.item;
    }

    public ServerboundDiscomposeItemPacket code(final String code) {
        this.code = code;
        return this;
    }

    public String code() {
        return this.code;
    }

    public ServerboundDiscomposeItemPacket opt(final int opt) {
        this.opt = opt;
        return this;
    }

    public int opt() {
        return this.opt;
    }

    @Override
    public int packetId() {
        return 316;
    }

    @Override
    public void read(final PacketBuf buf) {
        this.item = buf.readLong();
        this.code = buf.readString();
        this.opt = buf.readInt();
    }
}