package de.brickforceaurora.gameserver.net.protocol.clientbound;

import java.nio.charset.StandardCharsets;

import de.brickforceaurora.gameserver.net.protocol.IClientboundPacket;
import io.netty.buffer.ByteBuf;

public final class ClientboundMapHonorPacket implements IClientboundPacket {

    private int val;
    private int val2;
    private int val3;
    private int val4;
    private String val5;
    private String val6;
    private int val7;
    private int val8;
    private int val9;
    private int val10;
    private byte val11;
    private byte val12;
    private byte val13;
    private byte val14;
    private byte val15;
    private int val16;
    private int val17;
    private int val18;
    private int val19;
    private int val20;
    private int val21;

    public ClientboundMapHonorPacket val(final int val) {
        this.val = val;
        return this;
    }

    public int val() {
        return this.val;
    }

    public ClientboundMapHonorPacket val2(final int val2) {
        this.val2 = val2;
        return this;
    }

    public int val2() {
        return this.val2;
    }

    public ClientboundMapHonorPacket val3(final int val3) {
        this.val3 = val3;
        return this;
    }

    public int val3() {
        return this.val3;
    }

    public ClientboundMapHonorPacket val4(final int val4) {
        this.val4 = val4;
        return this;
    }

    public int val4() {
        return this.val4;
    }

    public ClientboundMapHonorPacket val5(final String val5) {
        this.val5 = val5;
        return this;
    }

    public String val5() {
        return this.val5;
    }

    public ClientboundMapHonorPacket val6(final String val6) {
        this.val6 = val6;
        return this;
    }

    public String val6() {
        return this.val6;
    }

    public ClientboundMapHonorPacket val7(final int val7) {
        if (val7 > 32767L || val7 < 0L) {
            throw new IllegalArgumentException("Value " + val7 + " is out of bounds of allowed number range of 0 - 32767");
        }
        this.val7 = val7;
        return this;
    }

    public int val7() {
        return this.val7;
    }

    public ClientboundMapHonorPacket val8(final int val8) {
        if (val8 > 255L || val8 < 0L) {
            throw new IllegalArgumentException("Value " + val8 + " is out of bounds of allowed number range of 0 - 255");
        }
        this.val8 = val8;
        return this;
    }

    public int val8() {
        return this.val8;
    }

    public ClientboundMapHonorPacket val9(final int val9) {
        if (val9 > 255L || val9 < 0L) {
            throw new IllegalArgumentException("Value " + val9 + " is out of bounds of allowed number range of 0 - 255");
        }
        this.val9 = val9;
        return this;
    }

    public int val9() {
        return this.val9;
    }

    public ClientboundMapHonorPacket val10(final int val10) {
        this.val10 = val10;
        return this;
    }

    public int val10() {
        return this.val10;
    }

    public ClientboundMapHonorPacket val11(final byte val11) {
        this.val11 = val11;
        return this;
    }

    public byte val11() {
        return this.val11;
    }

    public ClientboundMapHonorPacket val12(final byte val12) {
        this.val12 = val12;
        return this;
    }

    public byte val12() {
        return this.val12;
    }

    public ClientboundMapHonorPacket val13(final byte val13) {
        this.val13 = val13;
        return this;
    }

    public byte val13() {
        return this.val13;
    }

    public ClientboundMapHonorPacket val14(final byte val14) {
        this.val14 = val14;
        return this;
    }

    public byte val14() {
        return this.val14;
    }

    public ClientboundMapHonorPacket val15(final byte val15) {
        this.val15 = val15;
        return this;
    }

    public byte val15() {
        return this.val15;
    }

    public ClientboundMapHonorPacket val16(final int val16) {
        this.val16 = val16;
        return this;
    }

    public int val16() {
        return this.val16;
    }

    public ClientboundMapHonorPacket val17(final int val17) {
        this.val17 = val17;
        return this;
    }

    public int val17() {
        return this.val17;
    }

    public ClientboundMapHonorPacket val18(final int val18) {
        this.val18 = val18;
        return this;
    }

    public int val18() {
        return this.val18;
    }

    public ClientboundMapHonorPacket val19(final int val19) {
        this.val19 = val19;
        return this;
    }

    public int val19() {
        return this.val19;
    }

    public ClientboundMapHonorPacket val20(final int val20) {
        this.val20 = val20;
        return this;
    }

    public int val20() {
        return this.val20;
    }

    public ClientboundMapHonorPacket val21(final int val21) {
        this.val21 = val21;
        return this;
    }

    public int val21() {
        return this.val21;
    }

    @Override
    public int packetId() {
        return 436;
    }

    @Override
    public void write(final ByteBuf buffer) {
        buffer.writeIntLE(this.val);
        buffer.writeIntLE(this.val2);
        buffer.writeIntLE(this.val3);
        buffer.writeIntLE(this.val4);
        if (this.val5.isEmpty()) {
            buffer.writeIntLE(0);
        } else {
            final byte[] bytes = this.val5.getBytes(StandardCharsets.UTF_16LE);
            buffer.writeIntLE(bytes.length);
            buffer.writeBytes(bytes);
        }
        if (this.val6.isEmpty()) {
            buffer.writeIntLE(0);
        } else {
            final byte[] bytes = this.val6.getBytes(StandardCharsets.UTF_16LE);
            buffer.writeIntLE(bytes.length);
            buffer.writeBytes(bytes);
        }
        buffer.writeShortLE(this.val7 & 0xFFFF);
        buffer.writeByte(this.val8 & 0xFF);
        buffer.writeByte(this.val9 & 0xFF);
        buffer.writeIntLE(this.val10);
        buffer.writeByte(this.val11);
        buffer.writeByte(this.val12);
        buffer.writeByte(this.val13);
        buffer.writeByte(this.val14);
        buffer.writeByte(this.val15);
        buffer.writeIntLE(this.val16);
        buffer.writeIntLE(this.val17);
        buffer.writeIntLE(this.val18);
        buffer.writeIntLE(this.val19);
        buffer.writeIntLE(this.val20);
        buffer.writeIntLE(this.val21);
    }
}