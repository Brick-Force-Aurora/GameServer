package de.brickforceaurora.gameserver.net.protocol.clientbound;

import java.nio.charset.StandardCharsets;

import de.brickforceaurora.gameserver.net.protocol.IClientboundPacket;
import io.netty.buffer.ByteBuf;

public final class ClientboundUserPrivateMapPacket implements IClientboundPacket {

    private int val;
    private String val2;
    private int val3;
    private int val4;
    private byte val5;
    private byte val6;
    private byte val7;
    private byte val8;
    private byte val9;
    private byte val10;

    public ClientboundUserPrivateMapPacket val(final int val) {
        if (val > 255L || val < 0L) {
            throw new IllegalArgumentException("Value " + val + " is out of bounds of allowed number range of 0 - 255");
        }
        this.val = val;
        return this;
    }

    public int val() {
        return this.val;
    }

    public ClientboundUserPrivateMapPacket val2(final String val2) {
        this.val2 = val2;
        return this;
    }

    public String val2() {
        return this.val2;
    }

    public ClientboundUserPrivateMapPacket val3(final int val3) {
        this.val3 = val3;
        return this;
    }

    public int val3() {
        return this.val3;
    }

    public ClientboundUserPrivateMapPacket val4(final int val4) {
        this.val4 = val4;
        return this;
    }

    public int val4() {
        return this.val4;
    }

    public ClientboundUserPrivateMapPacket val5(final byte val5) {
        this.val5 = val5;
        return this;
    }

    public byte val5() {
        return this.val5;
    }

    public ClientboundUserPrivateMapPacket val6(final byte val6) {
        this.val6 = val6;
        return this;
    }

    public byte val6() {
        return this.val6;
    }

    public ClientboundUserPrivateMapPacket val7(final byte val7) {
        this.val7 = val7;
        return this;
    }

    public byte val7() {
        return this.val7;
    }

    public ClientboundUserPrivateMapPacket val8(final byte val8) {
        this.val8 = val8;
        return this;
    }

    public byte val8() {
        return this.val8;
    }

    public ClientboundUserPrivateMapPacket val9(final byte val9) {
        this.val9 = val9;
        return this;
    }

    public byte val9() {
        return this.val9;
    }

    public ClientboundUserPrivateMapPacket val10(final byte val10) {
        this.val10 = val10;
        return this;
    }

    public byte val10() {
        return this.val10;
    }

    @Override
    public int packetId() {
        return 41;
    }

    @Override
    public void write(final ByteBuf buffer) {
        buffer.writeByte(this.val & 0xFF);
        if (this.val2.isEmpty()) {
            buffer.writeIntLE(0);
        } else {
            final byte[] bytes = this.val2.getBytes(StandardCharsets.UTF_16LE);
            buffer.writeIntLE(bytes.length);
            buffer.writeBytes(bytes);
        }
        buffer.writeIntLE(this.val3);
        buffer.writeIntLE(this.val4);
        buffer.writeByte(this.val5);
        buffer.writeByte(this.val6);
        buffer.writeByte(this.val7);
        buffer.writeByte(this.val8);
        buffer.writeByte(this.val9);
        buffer.writeByte(this.val10);
    }
}