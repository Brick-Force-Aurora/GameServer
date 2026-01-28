package de.brickforceaurora.gameserver.net.protocol.serverbound;

import java.nio.charset.StandardCharsets;

import de.brickforceaurora.gameserver.net.protocol.IServerboundPacket;
import io.netty.buffer.ByteBuf;

public final class ServerboundUnpackBundlePacket implements IServerboundPacket {

    private long bundle;
    private String bundleCode;

    public ServerboundUnpackBundlePacket bundle(final long bundle) {
        this.bundle = bundle;
        return this;
    }

    public long bundle() {
        return this.bundle;
    }

    public ServerboundUnpackBundlePacket bundleCode(final String bundleCode) {
        this.bundleCode = bundleCode;
        return this;
    }

    public String bundleCode() {
        return this.bundleCode;
    }

    @Override
    public int packetId() {
        return 360;
    }

    @Override
    public void read(final ByteBuf buffer) {
        this.bundle = buffer.readLongLE();
        {
            final int length = buffer.readIntLE();
            if (length == 0) {
                this.bundleCode = "";
            } else {
                final byte[] bytes = new byte[length];
                buffer.readBytes(bytes);
                this.bundleCode = new String(bytes, StandardCharsets.UTF_16LE);
            }
        }
    }
}