package de.brickforceaurora.gameserver.net.protocol.serverbound;

import java.nio.charset.StandardCharsets;

import de.brickforceaurora.gameserver.net.protocol.IServerboundPacket;
import io.netty.buffer.ByteBuf;

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
    public void read(final ByteBuf buffer) {
        this.item = buffer.readLongLE();
        {
            final int length = buffer.readIntLE();
            if (length == 0) {
                this.code = "";
            } else {
                final byte[] bytes = new byte[length];
                buffer.readBytes(bytes);
                this.code = new String(bytes, StandardCharsets.UTF_16LE);
            }
        }
        this.opt = buffer.readIntLE();
    }
}