package de.brickforceaurora.gameserver.net.protocol.clientbound;

import java.nio.charset.StandardCharsets;

import de.brickforceaurora.gameserver.net.protocol.IClientboundPacket;
import io.netty.buffer.ByteBuf;

public final class ClientboundItemPacket implements IClientboundPacket {

    private long val;
    private String val2;
    private byte val3;
    private int val4;
    private byte val5;
    private int val6;

    public ClientboundItemPacket val(final long val) {
        this.val = val;
        return this;
    }

    public long val() {
        return this.val;
    }

    public ClientboundItemPacket val2(final String val2) {
        this.val2 = val2;
        return this;
    }

    public String val2() {
        return this.val2;
    }

    public ClientboundItemPacket val3(final byte val3) {
        this.val3 = val3;
        return this;
    }

    public byte val3() {
        return this.val3;
    }

    public ClientboundItemPacket val4(final int val4) {
        this.val4 = val4;
        return this;
    }

    public int val4() {
        return this.val4;
    }

    public ClientboundItemPacket val5(final byte val5) {
        this.val5 = val5;
        return this;
    }

    public byte val5() {
        return this.val5;
    }

    public ClientboundItemPacket val6(final int val6) {
        this.val6 = val6;
        return this;
    }

    public int val6() {
        return this.val6;
    }

    @Override
    public int packetId() {
        return 34;
    }

    @Override
    public void write(final ByteBuf buffer) {
        buffer.writeLongLE(this.val);
        if (this.val2.isEmpty()) {
            buffer.writeIntLE(0);
        } else {
            final byte[] bytes = this.val2.getBytes(StandardCharsets.UTF_16LE);
            buffer.writeIntLE(bytes.length);
            buffer.writeBytes(bytes);
        }
        buffer.writeByte(this.val3);
        buffer.writeIntLE(this.val4);
        buffer.writeByte(this.val5);
        buffer.writeIntLE(this.val6);
    }
}