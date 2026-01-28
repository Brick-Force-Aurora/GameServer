package de.brickforceaurora.gameserver.net.protocol.serverbound;

import java.nio.charset.StandardCharsets;

import de.brickforceaurora.gameserver.net.protocol.IServerboundPacket;
import io.netty.buffer.ByteBuf;

public final class ServerboundIncExtraSlotsPacket implements IServerboundPacket {

    private long item;
    private String itemCode;

    public ServerboundIncExtraSlotsPacket item(final long item) {
        this.item = item;
        return this;
    }

    public long item() {
        return this.item;
    }

    public ServerboundIncExtraSlotsPacket itemCode(final String itemCode) {
        this.itemCode = itemCode;
        return this;
    }

    public String itemCode() {
        return this.itemCode;
    }

    @Override
    public int packetId() {
        return 407;
    }

    @Override
    public void read(final ByteBuf buffer) {
        this.item = buffer.readLongLE();
        {
            final int length = buffer.readIntLE();
            if (length == 0) {
                this.itemCode = "";
            } else {
                final byte[] bytes = new byte[length];
                buffer.readBytes(bytes);
                this.itemCode = new String(bytes, StandardCharsets.UTF_16LE);
            }
        }
    }
}