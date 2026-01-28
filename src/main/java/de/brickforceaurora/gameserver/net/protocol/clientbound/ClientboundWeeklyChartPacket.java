package de.brickforceaurora.gameserver.net.protocol.clientbound;

import java.nio.charset.StandardCharsets;

import de.brickforceaurora.gameserver.net.protocol.IClientboundPacket;
import io.netty.buffer.ByteBuf;

public final class ClientboundWeeklyChartPacket implements IClientboundPacket {

    private int val;
    private int val2;
    private int val3;
    private byte val4;
    private byte val5;
    private int val6;
    private int val7;
    private int val8;
    private int val9;
    private String val10;
    private String val11;
    private int val12;
    private int val13;
    private int val14;
    private int val15;
    private byte val16;
    private byte val17;
    private byte val18;
    private byte val19;
    private byte val20;
    private int val21;
    private int val22;
    private int val23;
    private int val24;
    private int val25;
    private int val26;

    public ClientboundWeeklyChartPacket val(final int val) {
        this.val = val;
        return this;
    }

    public int val() {
        return this.val;
    }

    public ClientboundWeeklyChartPacket val2(final int val2) {
        this.val2 = val2;
        return this;
    }

    public int val2() {
        return this.val2;
    }

    public ClientboundWeeklyChartPacket val3(final int val3) {
        this.val3 = val3;
        return this;
    }

    public int val3() {
        return this.val3;
    }

    public ClientboundWeeklyChartPacket val4(final byte val4) {
        this.val4 = val4;
        return this;
    }

    public byte val4() {
        return this.val4;
    }

    public ClientboundWeeklyChartPacket val5(final byte val5) {
        this.val5 = val5;
        return this;
    }

    public byte val5() {
        return this.val5;
    }

    public ClientboundWeeklyChartPacket val6(final int val6) {
        this.val6 = val6;
        return this;
    }

    public int val6() {
        return this.val6;
    }

    public ClientboundWeeklyChartPacket val7(final int val7) {
        this.val7 = val7;
        return this;
    }

    public int val7() {
        return this.val7;
    }

    public ClientboundWeeklyChartPacket val8(final int val8) {
        this.val8 = val8;
        return this;
    }

    public int val8() {
        return this.val8;
    }

    public ClientboundWeeklyChartPacket val9(final int val9) {
        this.val9 = val9;
        return this;
    }

    public int val9() {
        return this.val9;
    }

    public ClientboundWeeklyChartPacket val10(final String val10) {
        this.val10 = val10;
        return this;
    }

    public String val10() {
        return this.val10;
    }

    public ClientboundWeeklyChartPacket val11(final String val11) {
        this.val11 = val11;
        return this;
    }

    public String val11() {
        return this.val11;
    }

    public ClientboundWeeklyChartPacket val12(final int val12) {
        if (val12 > 32767L || val12 < 0L) {
            throw new IllegalArgumentException("Value " + val12 + " is out of bounds of allowed number range of 0 - 32767");
        }
        this.val12 = val12;
        return this;
    }

    public int val12() {
        return this.val12;
    }

    public ClientboundWeeklyChartPacket val13(final int val13) {
        if (val13 > 255L || val13 < 0L) {
            throw new IllegalArgumentException("Value " + val13 + " is out of bounds of allowed number range of 0 - 255");
        }
        this.val13 = val13;
        return this;
    }

    public int val13() {
        return this.val13;
    }

    public ClientboundWeeklyChartPacket val14(final int val14) {
        if (val14 > 255L || val14 < 0L) {
            throw new IllegalArgumentException("Value " + val14 + " is out of bounds of allowed number range of 0 - 255");
        }
        this.val14 = val14;
        return this;
    }

    public int val14() {
        return this.val14;
    }

    public ClientboundWeeklyChartPacket val15(final int val15) {
        this.val15 = val15;
        return this;
    }

    public int val15() {
        return this.val15;
    }

    public ClientboundWeeklyChartPacket val16(final byte val16) {
        this.val16 = val16;
        return this;
    }

    public byte val16() {
        return this.val16;
    }

    public ClientboundWeeklyChartPacket val17(final byte val17) {
        this.val17 = val17;
        return this;
    }

    public byte val17() {
        return this.val17;
    }

    public ClientboundWeeklyChartPacket val18(final byte val18) {
        this.val18 = val18;
        return this;
    }

    public byte val18() {
        return this.val18;
    }

    public ClientboundWeeklyChartPacket val19(final byte val19) {
        this.val19 = val19;
        return this;
    }

    public byte val19() {
        return this.val19;
    }

    public ClientboundWeeklyChartPacket val20(final byte val20) {
        this.val20 = val20;
        return this;
    }

    public byte val20() {
        return this.val20;
    }

    public ClientboundWeeklyChartPacket val21(final int val21) {
        this.val21 = val21;
        return this;
    }

    public int val21() {
        return this.val21;
    }

    public ClientboundWeeklyChartPacket val22(final int val22) {
        this.val22 = val22;
        return this;
    }

    public int val22() {
        return this.val22;
    }

    public ClientboundWeeklyChartPacket val23(final int val23) {
        this.val23 = val23;
        return this;
    }

    public int val23() {
        return this.val23;
    }

    public ClientboundWeeklyChartPacket val24(final int val24) {
        this.val24 = val24;
        return this;
    }

    public int val24() {
        return this.val24;
    }

    public ClientboundWeeklyChartPacket val25(final int val25) {
        this.val25 = val25;
        return this;
    }

    public int val25() {
        return this.val25;
    }

    public ClientboundWeeklyChartPacket val26(final int val26) {
        this.val26 = val26;
        return this;
    }

    public int val26() {
        return this.val26;
    }

    @Override
    public int packetId() {
        return 434;
    }

    @Override
    public void write(final ByteBuf buffer) {
        buffer.writeIntLE(this.val);
        buffer.writeIntLE(this.val2);
        buffer.writeIntLE(this.val3);
        buffer.writeByte(this.val4);
        buffer.writeByte(this.val5);
        buffer.writeIntLE(this.val6);
        buffer.writeIntLE(this.val7);
        buffer.writeIntLE(this.val8);
        buffer.writeIntLE(this.val9);
        if (this.val10.isEmpty()) {
            buffer.writeIntLE(0);
        } else {
            final byte[] bytes = this.val10.getBytes(StandardCharsets.UTF_16LE);
            buffer.writeIntLE(bytes.length);
            buffer.writeBytes(bytes);
        }
        if (this.val11.isEmpty()) {
            buffer.writeIntLE(0);
        } else {
            final byte[] bytes = this.val11.getBytes(StandardCharsets.UTF_16LE);
            buffer.writeIntLE(bytes.length);
            buffer.writeBytes(bytes);
        }
        buffer.writeShortLE(this.val12 & 0xFFFF);
        buffer.writeByte(this.val13 & 0xFF);
        buffer.writeByte(this.val14 & 0xFF);
        buffer.writeIntLE(this.val15);
        buffer.writeByte(this.val16);
        buffer.writeByte(this.val17);
        buffer.writeByte(this.val18);
        buffer.writeByte(this.val19);
        buffer.writeByte(this.val20);
        buffer.writeIntLE(this.val21);
        buffer.writeIntLE(this.val22);
        buffer.writeIntLE(this.val23);
        buffer.writeIntLE(this.val24);
        buffer.writeIntLE(this.val25);
        buffer.writeIntLE(this.val26);
    }
}