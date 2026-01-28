package de.brickforceaurora.gameserver.net.protocol.clientbound;

import java.nio.charset.StandardCharsets;

import de.brickforceaurora.gameserver.net.protocol.IClientboundPacket;
import io.netty.buffer.ByteBuf;

public final class ClientboundEnterPacket implements IClientboundPacket {

    private int val;
    private String val2;
    private String val3;
    private int val4;
    private String val5;
    private int val6;
    private int val7;
    final String UnknownValue0 = "array[i]";
    private int val8;
    private int val9;
    private int val10;
    private String val11;
    private int val12;
    private int val13;
    private int val14;
    final String UnknownValue1 = "val7";
    final String UnknownValue2 = "array2[j]";
    final String UnknownValue3 = "val7";
    final String UnknownValue4 = "array3[k]";

    public ClientboundEnterPacket val(final int val) {
        this.val = val;
        return this;
    }

    public int val() {
        return this.val;
    }

    public ClientboundEnterPacket val2(final String val2) {
        this.val2 = val2;
        return this;
    }

    public String val2() {
        return this.val2;
    }

    public ClientboundEnterPacket val3(final String val3) {
        this.val3 = val3;
        return this;
    }

    public String val3() {
        return this.val3;
    }

    public ClientboundEnterPacket val4(final int val4) {
        this.val4 = val4;
        return this;
    }

    public int val4() {
        return this.val4;
    }

    public ClientboundEnterPacket val5(final String val5) {
        this.val5 = val5;
        return this;
    }

    public String val5() {
        return this.val5;
    }

    public ClientboundEnterPacket val6(final int val6) {
        this.val6 = val6;
        return this;
    }

    public int val6() {
        return this.val6;
    }

    public ClientboundEnterPacket val7(final int val7) {
        this.val7 = val7;
        return this;
    }

    public int val7() {
        return this.val7;
    }

    public ClientboundEnterPacket val8(final int val8) {
        this.val8 = val8;
        return this;
    }

    public int val8() {
        return this.val8;
    }

    public ClientboundEnterPacket val9(final int val9) {
        this.val9 = val9;
        return this;
    }

    public int val9() {
        return this.val9;
    }

    public ClientboundEnterPacket val10(final int val10) {
        this.val10 = val10;
        return this;
    }

    public int val10() {
        return this.val10;
    }

    public ClientboundEnterPacket val11(final String val11) {
        this.val11 = val11;
        return this;
    }

    public String val11() {
        return this.val11;
    }

    public ClientboundEnterPacket val12(final int val12) {
        this.val12 = val12;
        return this;
    }

    public int val12() {
        return this.val12;
    }

    public ClientboundEnterPacket val13(final int val13) {
        this.val13 = val13;
        return this;
    }

    public int val13() {
        return this.val13;
    }

    public ClientboundEnterPacket val14(final int val14) {
        if (val14 > 255L || val14 < 0L) {
            throw new IllegalArgumentException("Value " + val14 + " is out of bounds of allowed number range of 0 - 255");
        }
        this.val14 = val14;
        return this;
    }

    public int val14() {
        return this.val14;
    }

    @Override
    public int packetId() {
        return 10;
    }

    @Override
    public void write(final ByteBuf buffer) {
        buffer.writeIntLE(this.val);
        if (this.val2.isEmpty()) {
            buffer.writeIntLE(0);
        } else {
            final byte[] bytes = this.val2.getBytes(StandardCharsets.UTF_16LE);
            buffer.writeIntLE(bytes.length);
            buffer.writeBytes(bytes);
        }
        if (this.val3.isEmpty()) {
            buffer.writeIntLE(0);
        } else {
            final byte[] bytes = this.val3.getBytes(StandardCharsets.UTF_16LE);
            buffer.writeIntLE(bytes.length);
            buffer.writeBytes(bytes);
        }
        buffer.writeIntLE(this.val4);
        if (this.val5.isEmpty()) {
            buffer.writeIntLE(0);
        } else {
            final byte[] bytes = this.val5.getBytes(StandardCharsets.UTF_16LE);
            buffer.writeIntLE(bytes.length);
            buffer.writeBytes(bytes);
        }
        buffer.writeIntLE(this.val6);
        buffer.writeIntLE(this.val7);
        buffer.writeIntLE(this.val8);
        buffer.writeIntLE(this.val9);
        buffer.writeIntLE(this.val10);
        if (this.val11.isEmpty()) {
            buffer.writeIntLE(0);
        } else {
            final byte[] bytes = this.val11.getBytes(StandardCharsets.UTF_16LE);
            buffer.writeIntLE(bytes.length);
            buffer.writeBytes(bytes);
        }
        buffer.writeIntLE(this.val12);
        buffer.writeIntLE(this.val13);
        buffer.writeByte(this.val14 & 0xFF);
    }
}