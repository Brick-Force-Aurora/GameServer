package de.brickforceaurora.gameserver.net.protocol.serverbound;

import java.nio.charset.StandardCharsets;

import de.brickforceaurora.gameserver.net.protocol.IServerboundPacket;
import io.netty.buffer.ByteBuf;

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
    public void read(final ByteBuf buffer) {
        this.src = buffer.readLongLE();
        this.dst = buffer.readLongLE();
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
    }
}