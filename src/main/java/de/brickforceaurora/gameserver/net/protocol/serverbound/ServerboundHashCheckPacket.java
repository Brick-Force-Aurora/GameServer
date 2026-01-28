package de.brickforceaurora.gameserver.net.protocol.serverbound;

import java.nio.charset.StandardCharsets;

import de.brickforceaurora.gameserver.net.protocol.IServerboundPacket;
import io.netty.buffer.ByteBuf;

public final class ServerboundHashCheckPacket implements IServerboundPacket {

    private String hash;

    public ServerboundHashCheckPacket hash(final String hash) {
        this.hash = hash;
        return this;
    }

    public String hash() {
        return this.hash;
    }

    @Override
    public int packetId() {
        return 395;
    }

    @Override
    public void read(final ByteBuf buffer) {
        {
            final int length = buffer.readIntLE();
            if (length == 0) {
                this.hash = "";
            } else {
                final byte[] bytes = new byte[length];
                buffer.readBytes(bytes);
                this.hash = new String(bytes, StandardCharsets.UTF_16LE);
            }
        }
    }
}