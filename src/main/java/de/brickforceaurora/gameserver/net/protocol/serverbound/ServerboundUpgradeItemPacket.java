package de.brickforceaurora.gameserver.net.protocol.serverbound;

import java.nio.charset.StandardCharsets;

import de.brickforceaurora.gameserver.net.protocol.IServerboundPacket;
import io.netty.buffer.ByteBuf;

public final class ServerboundUpgradeItemPacket implements IServerboundPacket {

    private long item;
    private String code;
    private long upgrader;
    private String upgradercode;
    private int prop;

    public ServerboundUpgradeItemPacket item(final long item) {
        this.item = item;
        return this;
    }

    public long item() {
        return this.item;
    }

    public ServerboundUpgradeItemPacket code(final String code) {
        this.code = code;
        return this;
    }

    public String code() {
        return this.code;
    }

    public ServerboundUpgradeItemPacket upgrader(final long upgrader) {
        this.upgrader = upgrader;
        return this;
    }

    public long upgrader() {
        return this.upgrader;
    }

    public ServerboundUpgradeItemPacket upgradercode(final String upgradercode) {
        this.upgradercode = upgradercode;
        return this;
    }

    public String upgradercode() {
        return this.upgradercode;
    }

    public ServerboundUpgradeItemPacket prop(final int prop) {
        this.prop = prop;
        return this;
    }

    public int prop() {
        return this.prop;
    }

    @Override
    public int packetId() {
        return 353;
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
        this.upgrader = buffer.readLongLE();
        {
            final int length = buffer.readIntLE();
            if (length == 0) {
                this.upgradercode = "";
            } else {
                final byte[] bytes = new byte[length];
                buffer.readBytes(bytes);
                this.upgradercode = new String(bytes, StandardCharsets.UTF_16LE);
            }
        }
        this.prop = buffer.readIntLE();
    }
}