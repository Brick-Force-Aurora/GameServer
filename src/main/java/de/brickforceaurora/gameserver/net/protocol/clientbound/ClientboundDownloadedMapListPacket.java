package de.brickforceaurora.gameserver.net.protocol.clientbound;

import java.nio.charset.StandardCharsets;

import de.brickforceaurora.gameserver.net.protocol.IClientboundPacket;
import io.netty.buffer.ByteBuf;

public final class ClientboundDownloadedMapListPacket implements IClientboundPacket {

    private int val;
    private int val2;
    private String val3;
    private String val4;
    private int val5;
    private int val6;
    private int val7;
    private int val8;
    private byte val9;
    private byte val10;
    private byte val11;
    private byte val12;
    private byte val13;
    private int val14;
    private int val15;
    private int val16;
    private int val17;
    private int val18;
    private int val19;

    public ClientboundDownloadedMapListPacket val(final int val) {
        this.val = val;
        return this;
    }

    public int val() {
        return this.val;
    }

    public ClientboundDownloadedMapListPacket val2(final int val2) {
        this.val2 = val2;
        return this;
    }

    public int val2() {
        return this.val2;
    }

    public ClientboundDownloadedMapListPacket val3(final String val3) {
        this.val3 = val3;
        return this;
    }

    public String val3() {
        return this.val3;
    }

    public ClientboundDownloadedMapListPacket val4(final String val4) {
        this.val4 = val4;
        return this;
    }

    public String val4() {
        return this.val4;
    }

    public ClientboundDownloadedMapListPacket val5(final int val5) {
        if (val5 > 32767L || val5 < 0L) {
            throw new IllegalArgumentException("Value " + val5 + " is out of bounds of allowed number range of 0 - 32767");
        }
        this.val5 = val5;
        return this;
    }

    public int val5() {
        return this.val5;
    }

    public ClientboundDownloadedMapListPacket val6(final int val6) {
        if (val6 > 255L || val6 < 0L) {
            throw new IllegalArgumentException("Value " + val6 + " is out of bounds of allowed number range of 0 - 255");
        }
        this.val6 = val6;
        return this;
    }

    public int val6() {
        return this.val6;
    }

    public ClientboundDownloadedMapListPacket val7(final int val7) {
        if (val7 > 255L || val7 < 0L) {
            throw new IllegalArgumentException("Value " + val7 + " is out of bounds of allowed number range of 0 - 255");
        }
        this.val7 = val7;
        return this;
    }

    public int val7() {
        return this.val7;
    }

    public ClientboundDownloadedMapListPacket val8(final int val8) {
        this.val8 = val8;
        return this;
    }

    public int val8() {
        return this.val8;
    }

    public ClientboundDownloadedMapListPacket val9(final byte val9) {
        this.val9 = val9;
        return this;
    }

    public byte val9() {
        return this.val9;
    }

    public ClientboundDownloadedMapListPacket val10(final byte val10) {
        this.val10 = val10;
        return this;
    }

    public byte val10() {
        return this.val10;
    }

    public ClientboundDownloadedMapListPacket val11(final byte val11) {
        this.val11 = val11;
        return this;
    }

    public byte val11() {
        return this.val11;
    }

    public ClientboundDownloadedMapListPacket val12(final byte val12) {
        this.val12 = val12;
        return this;
    }

    public byte val12() {
        return this.val12;
    }

    public ClientboundDownloadedMapListPacket val13(final byte val13) {
        this.val13 = val13;
        return this;
    }

    public byte val13() {
        return this.val13;
    }

    public ClientboundDownloadedMapListPacket val14(final int val14) {
        this.val14 = val14;
        return this;
    }

    public int val14() {
        return this.val14;
    }

    public ClientboundDownloadedMapListPacket val15(final int val15) {
        this.val15 = val15;
        return this;
    }

    public int val15() {
        return this.val15;
    }

    public ClientboundDownloadedMapListPacket val16(final int val16) {
        this.val16 = val16;
        return this;
    }

    public int val16() {
        return this.val16;
    }

    public ClientboundDownloadedMapListPacket val17(final int val17) {
        this.val17 = val17;
        return this;
    }

    public int val17() {
        return this.val17;
    }

    public ClientboundDownloadedMapListPacket val18(final int val18) {
        this.val18 = val18;
        return this;
    }

    public int val18() {
        return this.val18;
    }

    public ClientboundDownloadedMapListPacket val19(final int val19) {
        this.val19 = val19;
        return this;
    }

    public int val19() {
        return this.val19;
    }

    @Override
    public int packetId() {
        return 465;
    }

    @Override
    public void write(final ByteBuf buffer) {
        buffer.writeIntLE(this.val);
        buffer.writeIntLE(this.val2);
        if (this.val3.isEmpty()) {
            buffer.writeIntLE(0);
        } else {
            final byte[] bytes = this.val3.getBytes(StandardCharsets.UTF_16LE);
            buffer.writeIntLE(bytes.length);
            buffer.writeBytes(bytes);
        }
        if (this.val4.isEmpty()) {
            buffer.writeIntLE(0);
        } else {
            final byte[] bytes = this.val4.getBytes(StandardCharsets.UTF_16LE);
            buffer.writeIntLE(bytes.length);
            buffer.writeBytes(bytes);
        }
        buffer.writeShortLE(this.val5 & 0xFFFF);
        buffer.writeByte(this.val6 & 0xFF);
        buffer.writeByte(this.val7 & 0xFF);
        buffer.writeIntLE(this.val8);
        buffer.writeByte(this.val9);
        buffer.writeByte(this.val10);
        buffer.writeByte(this.val11);
        buffer.writeByte(this.val12);
        buffer.writeByte(this.val13);
        buffer.writeIntLE(this.val14);
        buffer.writeIntLE(this.val15);
        buffer.writeIntLE(this.val16);
        buffer.writeIntLE(this.val17);
        buffer.writeIntLE(this.val18);
        buffer.writeIntLE(this.val19);
    }
}