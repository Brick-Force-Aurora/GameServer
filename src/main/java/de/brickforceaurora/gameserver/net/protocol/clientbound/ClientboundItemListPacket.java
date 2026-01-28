package de.brickforceaurora.gameserver.net.protocol.clientbound;

import java.nio.charset.StandardCharsets;

import de.brickforceaurora.gameserver.net.protocol.IClientboundPacket;
import io.netty.buffer.ByteBuf;

public final class ClientboundItemListPacket implements IClientboundPacket {

    private int val;
    private long val2;
    private String val3;
    private byte val4;
    private int val5;
    private byte val6;
    private int val7;

    public ClientboundItemListPacket val(final int val) {
        this.val = val;
        return this;
    }

    public int val() {
        return this.val;
    }

    public ClientboundItemListPacket val2(final long val2) {
        this.val2 = val2;
        return this;
    }

    public long val2() {
        return this.val2;
    }

    public ClientboundItemListPacket val3(final String val3) {
        this.val3 = val3;
        return this;
    }

    public String val3() {
        return this.val3;
    }

    public ClientboundItemListPacket val4(final byte val4) {
        this.val4 = val4;
        return this;
    }

    public byte val4() {
        return this.val4;
    }

    public ClientboundItemListPacket val5(final int val5) {
        this.val5 = val5;
        return this;
    }

    public int val5() {
        return this.val5;
    }

    public ClientboundItemListPacket val6(final byte val6) {
        this.val6 = val6;
        return this;
    }

    public byte val6() {
        return this.val6;
    }

    public ClientboundItemListPacket val7(final int val7) {
        this.val7 = val7;
        return this;
    }

    public int val7() {
        return this.val7;
    }

    @Override
    public int packetId() {
        return 464;
    }

    @Override
    public void write(final ByteBuf buffer) {
        buffer.writeIntLE(this.val);
        buffer.writeLongLE(this.val2);
        if (this.val3.isEmpty()) {
            buffer.writeIntLE(0);
        } else {
            final byte[] bytes = this.val3.getBytes(StandardCharsets.UTF_16LE);
            buffer.writeIntLE(bytes.length);
            buffer.writeBytes(bytes);
        }
        buffer.writeByte(this.val4);
        buffer.writeIntLE(this.val5);
        buffer.writeByte(this.val6);
        buffer.writeIntLE(this.val7);
    }
}