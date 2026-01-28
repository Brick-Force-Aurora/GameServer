package de.brickforceaurora.gameserver.net.protocol.serverbound;

import java.nio.charset.StandardCharsets;

import de.brickforceaurora.gameserver.net.protocol.IServerboundPacket;
import io.netty.buffer.ByteBuf;

public final class ServerboundDropItemPacket implements IServerboundPacket {

    private String itemCode;
    private int count;
    private int subCount;
    private float x;
    private float y;
    private float z;

    public ServerboundDropItemPacket itemCode(final String itemCode) {
        this.itemCode = itemCode;
        return this;
    }

    public String itemCode() {
        return this.itemCode;
    }

    public ServerboundDropItemPacket count(final int count) {
        this.count = count;
        return this;
    }

    public int count() {
        return this.count;
    }

    public ServerboundDropItemPacket subCount(final int subCount) {
        this.subCount = subCount;
        return this;
    }

    public int subCount() {
        return this.subCount;
    }

    public ServerboundDropItemPacket x(final float x) {
        this.x = x;
        return this;
    }

    public float x() {
        return this.x;
    }

    public ServerboundDropItemPacket y(final float y) {
        this.y = y;
        return this;
    }

    public float y() {
        return this.y;
    }

    public ServerboundDropItemPacket z(final float z) {
        this.z = z;
        return this;
    }

    public float z() {
        return this.z;
    }

    @Override
    public int packetId() {
        return 526;
    }

    @Override
    public void read(final ByteBuf buffer) {
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
        this.count = buffer.readIntLE();
        this.subCount = buffer.readIntLE();
        this.x = buffer.readFloatLE();
        this.y = buffer.readFloatLE();
        this.z = buffer.readFloatLE();
    }
}