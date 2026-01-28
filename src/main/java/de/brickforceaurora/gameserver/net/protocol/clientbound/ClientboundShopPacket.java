package de.brickforceaurora.gameserver.net.protocol.clientbound;

import java.nio.charset.StandardCharsets;

import de.brickforceaurora.gameserver.net.protocol.IClientboundPacket;
import io.netty.buffer.ByteBuf;

public final class ClientboundShopPacket implements IClientboundPacket {

    private int val;
    private String val2;
    private byte val3;
    private byte val4;
    private byte val5;
    private int val6;
    private byte val7;
    private byte val8;
    private byte val9;
    private byte val10;
    private int val11;
    private int val12;
    private int val13;
    private int val14;
    private int val15;
    private int val16;
    private int val17;
    private int val18;
    private int val19;
    private int val20;
    private int val21;
    private int val22;
    private byte val23;
    private byte val24;
    private byte val25;

    public ClientboundShopPacket val(final int val) {
        this.val = val;
        return this;
    }

    public int val() {
        return this.val;
    }

    public ClientboundShopPacket val2(final String val2) {
        this.val2 = val2;
        return this;
    }

    public String val2() {
        return this.val2;
    }

    public ClientboundShopPacket val3(final byte val3) {
        this.val3 = val3;
        return this;
    }

    public byte val3() {
        return this.val3;
    }

    public ClientboundShopPacket val4(final byte val4) {
        this.val4 = val4;
        return this;
    }

    public byte val4() {
        return this.val4;
    }

    public ClientboundShopPacket val5(final byte val5) {
        this.val5 = val5;
        return this;
    }

    public byte val5() {
        return this.val5;
    }

    public ClientboundShopPacket val6(final int val6) {
        if (val6 > 255L || val6 < 0L) {
            throw new IllegalArgumentException("Value " + val6 + " is out of bounds of allowed number range of 0 - 255");
        }
        this.val6 = val6;
        return this;
    }

    public int val6() {
        return this.val6;
    }

    public ClientboundShopPacket val7(final byte val7) {
        this.val7 = val7;
        return this;
    }

    public byte val7() {
        return this.val7;
    }

    public ClientboundShopPacket val8(final byte val8) {
        this.val8 = val8;
        return this;
    }

    public byte val8() {
        return this.val8;
    }

    public ClientboundShopPacket val9(final byte val9) {
        this.val9 = val9;
        return this;
    }

    public byte val9() {
        return this.val9;
    }

    public ClientboundShopPacket val10(final byte val10) {
        this.val10 = val10;
        return this;
    }

    public byte val10() {
        return this.val10;
    }

    public ClientboundShopPacket val11(final int val11) {
        this.val11 = val11;
        return this;
    }

    public int val11() {
        return this.val11;
    }

    public ClientboundShopPacket val12(final int val12) {
        this.val12 = val12;
        return this;
    }

    public int val12() {
        return this.val12;
    }

    public ClientboundShopPacket val13(final int val13) {
        this.val13 = val13;
        return this;
    }

    public int val13() {
        return this.val13;
    }

    public ClientboundShopPacket val14(final int val14) {
        this.val14 = val14;
        return this;
    }

    public int val14() {
        return this.val14;
    }

    public ClientboundShopPacket val15(final int val15) {
        this.val15 = val15;
        return this;
    }

    public int val15() {
        return this.val15;
    }

    public ClientboundShopPacket val16(final int val16) {
        this.val16 = val16;
        return this;
    }

    public int val16() {
        return this.val16;
    }

    public ClientboundShopPacket val17(final int val17) {
        this.val17 = val17;
        return this;
    }

    public int val17() {
        return this.val17;
    }

    public ClientboundShopPacket val18(final int val18) {
        this.val18 = val18;
        return this;
    }

    public int val18() {
        return this.val18;
    }

    public ClientboundShopPacket val19(final int val19) {
        this.val19 = val19;
        return this;
    }

    public int val19() {
        return this.val19;
    }

    public ClientboundShopPacket val20(final int val20) {
        this.val20 = val20;
        return this;
    }

    public int val20() {
        return this.val20;
    }

    public ClientboundShopPacket val21(final int val21) {
        this.val21 = val21;
        return this;
    }

    public int val21() {
        return this.val21;
    }

    public ClientboundShopPacket val22(final int val22) {
        this.val22 = val22;
        return this;
    }

    public int val22() {
        return this.val22;
    }

    public ClientboundShopPacket val23(final byte val23) {
        this.val23 = val23;
        return this;
    }

    public byte val23() {
        return this.val23;
    }

    public ClientboundShopPacket val24(final byte val24) {
        this.val24 = val24;
        return this;
    }

    public byte val24() {
        return this.val24;
    }

    public ClientboundShopPacket val25(final byte val25) {
        this.val25 = val25;
        return this;
    }

    public byte val25() {
        return this.val25;
    }

    @Override
    public int packetId() {
        return 318;
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
        buffer.writeByte(this.val3);
        buffer.writeByte(this.val4);
        buffer.writeByte(this.val5);
        buffer.writeByte(this.val6 & 0xFF);
        buffer.writeByte(this.val7);
        buffer.writeByte(this.val8);
        buffer.writeByte(this.val9);
        buffer.writeByte(this.val10);
        buffer.writeIntLE(this.val11);
        buffer.writeIntLE(this.val12);
        buffer.writeIntLE(this.val13);
        buffer.writeIntLE(this.val14);
        buffer.writeIntLE(this.val15);
        buffer.writeIntLE(this.val16);
        buffer.writeIntLE(this.val17);
        buffer.writeIntLE(this.val18);
        buffer.writeIntLE(this.val19);
        buffer.writeIntLE(this.val20);
        buffer.writeIntLE(this.val21);
        buffer.writeIntLE(this.val22);
        buffer.writeByte(this.val23);
        buffer.writeByte(this.val24);
        buffer.writeByte(this.val25);
    }
}