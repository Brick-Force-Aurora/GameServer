package de.brickforceaurora.gameserver.net.protocol.clientbound;

import java.nio.charset.StandardCharsets;

import de.brickforceaurora.gameserver.net.protocol.IClientboundPacket;
import io.netty.buffer.ByteBuf;

public final class ClientboundDownloadedMapPacket implements IClientboundPacket {

    private int val;
    private String val2;
    private String val3;
    private int val4;
    private int val5;
    private int val6;
    private int val7;
    private byte val8;
    private byte val9;
    private byte val10;
    private byte val11;
    private byte val12;
    private int val13;
    private int val14;
    private int val15;
    private int val16;
    private int val17;
    private int val18;

    public ClientboundDownloadedMapPacket val(final int val) {
        this.val = val;
        return this;
    }

    public int val() {
        return this.val;
    }

    public ClientboundDownloadedMapPacket val2(final String val2) {
        this.val2 = val2;
        return this;
    }

    public String val2() {
        return this.val2;
    }

    public ClientboundDownloadedMapPacket val3(final String val3) {
        this.val3 = val3;
        return this;
    }

    public String val3() {
        return this.val3;
    }

    public ClientboundDownloadedMapPacket val4(final int val4) {
        if (val4 > 32767L || val4 < 0L) {
            throw new IllegalArgumentException("Value " + val4 + " is out of bounds of allowed number range of 0 - 32767");
        }
        this.val4 = val4;
        return this;
    }

    public int val4() {
        return this.val4;
    }

    public ClientboundDownloadedMapPacket val5(final int val5) {
        if (val5 > 255L || val5 < 0L) {
            throw new IllegalArgumentException("Value " + val5 + " is out of bounds of allowed number range of 0 - 255");
        }
        this.val5 = val5;
        return this;
    }

    public int val5() {
        return this.val5;
    }

    public ClientboundDownloadedMapPacket val6(final int val6) {
        if (val6 > 255L || val6 < 0L) {
            throw new IllegalArgumentException("Value " + val6 + " is out of bounds of allowed number range of 0 - 255");
        }
        this.val6 = val6;
        return this;
    }

    public int val6() {
        return this.val6;
    }

    public ClientboundDownloadedMapPacket val7(final int val7) {
        this.val7 = val7;
        return this;
    }

    public int val7() {
        return this.val7;
    }

    public ClientboundDownloadedMapPacket val8(final byte val8) {
        this.val8 = val8;
        return this;
    }

    public byte val8() {
        return this.val8;
    }

    public ClientboundDownloadedMapPacket val9(final byte val9) {
        this.val9 = val9;
        return this;
    }

    public byte val9() {
        return this.val9;
    }

    public ClientboundDownloadedMapPacket val10(final byte val10) {
        this.val10 = val10;
        return this;
    }

    public byte val10() {
        return this.val10;
    }

    public ClientboundDownloadedMapPacket val11(final byte val11) {
        this.val11 = val11;
        return this;
    }

    public byte val11() {
        return this.val11;
    }

    public ClientboundDownloadedMapPacket val12(final byte val12) {
        this.val12 = val12;
        return this;
    }

    public byte val12() {
        return this.val12;
    }

    public ClientboundDownloadedMapPacket val13(final int val13) {
        this.val13 = val13;
        return this;
    }

    public int val13() {
        return this.val13;
    }

    public ClientboundDownloadedMapPacket val14(final int val14) {
        this.val14 = val14;
        return this;
    }

    public int val14() {
        return this.val14;
    }

    public ClientboundDownloadedMapPacket val15(final int val15) {
        this.val15 = val15;
        return this;
    }

    public int val15() {
        return this.val15;
    }

    public ClientboundDownloadedMapPacket val16(final int val16) {
        this.val16 = val16;
        return this;
    }

    public int val16() {
        return this.val16;
    }

    public ClientboundDownloadedMapPacket val17(final int val17) {
        this.val17 = val17;
        return this;
    }

    public int val17() {
        return this.val17;
    }

    public ClientboundDownloadedMapPacket val18(final int val18) {
        this.val18 = val18;
        return this;
    }

    public int val18() {
        return this.val18;
    }

    @Override
    public int packetId() {
        return 173;
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
        buffer.writeShortLE(this.val4 & 0xFFFF);
        buffer.writeByte(this.val5 & 0xFF);
        buffer.writeByte(this.val6 & 0xFF);
        buffer.writeIntLE(this.val7);
        buffer.writeByte(this.val8);
        buffer.writeByte(this.val9);
        buffer.writeByte(this.val10);
        buffer.writeByte(this.val11);
        buffer.writeByte(this.val12);
        buffer.writeIntLE(this.val13);
        buffer.writeIntLE(this.val14);
        buffer.writeIntLE(this.val15);
        buffer.writeIntLE(this.val16);
        buffer.writeIntLE(this.val17);
        buffer.writeIntLE(this.val18);
    }
}