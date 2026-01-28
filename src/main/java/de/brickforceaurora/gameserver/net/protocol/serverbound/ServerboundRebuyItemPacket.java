package de.brickforceaurora.gameserver.net.protocol.serverbound;

import java.nio.charset.StandardCharsets;

import de.brickforceaurora.gameserver.net.protocol.IServerboundPacket;
import io.netty.buffer.ByteBuf;

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
    public void read(final ByteBuf buffer) {
        this.itemSeq = buffer.readLongLE();
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
        this.buyHow = buffer.readIntLE();
        this.option = buffer.readIntLE();
        this.val = buffer.readUnsignedByte();
        this.needEqup = buffer.readBoolean();
    }
}