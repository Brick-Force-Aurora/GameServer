package de.brickforceaurora.gameserver.net.protocol.clientbound;

import java.nio.charset.StandardCharsets;

import de.brickforceaurora.gameserver.net.protocol.IClientboundPacket;
import io.netty.buffer.ByteBuf;

public final class ClientboundMapDetailPacket implements IClientboundPacket {

    private int Unnamed0;
    private String val2;
    private int val3;
    private int val4;
    private int val5;
    private String val6;
    private String val7;
    private int val8;

    public ClientboundMapDetailPacket Unnamed0(final int Unnamed0) {
        this.Unnamed0 = Unnamed0;
        return this;
    }

    public int Unnamed0() {
        return this.Unnamed0;
    }

    public ClientboundMapDetailPacket val2(final String val2) {
        this.val2 = val2;
        return this;
    }

    public String val2() {
        return this.val2;
    }

    public ClientboundMapDetailPacket val3(final int val3) {
        this.val3 = val3;
        return this;
    }

    public int val3() {
        return this.val3;
    }

    public ClientboundMapDetailPacket val4(final int val4) {
        this.val4 = val4;
        return this;
    }

    public int val4() {
        return this.val4;
    }

    public ClientboundMapDetailPacket val5(final int val5) {
        this.val5 = val5;
        return this;
    }

    public int val5() {
        return this.val5;
    }

    public ClientboundMapDetailPacket val6(final String val6) {
        this.val6 = val6;
        return this;
    }

    public String val6() {
        return this.val6;
    }

    public ClientboundMapDetailPacket val7(final String val7) {
        this.val7 = val7;
        return this;
    }

    public String val7() {
        return this.val7;
    }

    public ClientboundMapDetailPacket val8(final int val8) {
        if (val8 > 255L || val8 < 0L) {
            throw new IllegalArgumentException("Value " + val8 + " is out of bounds of allowed number range of 0 - 255");
        }
        this.val8 = val8;
        return this;
    }

    public int val8() {
        return this.val8;
    }

    @Override
    public int packetId() {
        return 438;
    }

    @Override
    public void write(final ByteBuf buffer) {
        buffer.writeIntLE(this.Unnamed0);
        if (this.val2.isEmpty()) {
            buffer.writeIntLE(0);
        } else {
            final byte[] bytes = this.val2.getBytes(StandardCharsets.UTF_16LE);
            buffer.writeIntLE(bytes.length);
            buffer.writeBytes(bytes);
        }
        buffer.writeIntLE(this.val3);
        buffer.writeIntLE(this.val4);
        buffer.writeIntLE(this.val5);
        if (this.val6.isEmpty()) {
            buffer.writeIntLE(0);
        } else {
            final byte[] bytes = this.val6.getBytes(StandardCharsets.UTF_16LE);
            buffer.writeIntLE(bytes.length);
            buffer.writeBytes(bytes);
        }
        if (this.val7.isEmpty()) {
            buffer.writeIntLE(0);
        } else {
            final byte[] bytes = this.val7.getBytes(StandardCharsets.UTF_16LE);
            buffer.writeIntLE(bytes.length);
            buffer.writeBytes(bytes);
        }
        buffer.writeByte(this.val8 & 0xFF);
    }
}