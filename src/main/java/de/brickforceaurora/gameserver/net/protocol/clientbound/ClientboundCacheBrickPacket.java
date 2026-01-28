package de.brickforceaurora.gameserver.net.protocol.clientbound;

import java.nio.charset.StandardCharsets;

import de.brickforceaurora.gameserver.net.protocol.IClientboundPacket;
import io.netty.buffer.ByteBuf;

public final class ClientboundCacheBrickPacket implements IClientboundPacket {

    private int val;
    private int val2;
    private int val3;
    private int val4;
    private int val5;
    private int val6;
    private int val7;
    private int val8;
    private int val9;
    private String val10;
    private boolean val11;
    private boolean val12;
    private String val13;

    public ClientboundCacheBrickPacket val(final int val) {
        this.val = val;
        return this;
    }

    public int val() {
        return this.val;
    }

    public ClientboundCacheBrickPacket val2(final int val2) {
        this.val2 = val2;
        return this;
    }

    public int val2() {
        return this.val2;
    }

    public ClientboundCacheBrickPacket val3(final int val3) {
        if (val3 > 255L || val3 < 0L) {
            throw new IllegalArgumentException("Value " + val3 + " is out of bounds of allowed number range of 0 - 255");
        }
        this.val3 = val3;
        return this;
    }

    public int val3() {
        return this.val3;
    }

    public ClientboundCacheBrickPacket val4(final int val4) {
        if (val4 > 255L || val4 < 0L) {
            throw new IllegalArgumentException("Value " + val4 + " is out of bounds of allowed number range of 0 - 255");
        }
        this.val4 = val4;
        return this;
    }

    public int val4() {
        return this.val4;
    }

    public ClientboundCacheBrickPacket val5(final int val5) {
        if (val5 > 255L || val5 < 0L) {
            throw new IllegalArgumentException("Value " + val5 + " is out of bounds of allowed number range of 0 - 255");
        }
        this.val5 = val5;
        return this;
    }

    public int val5() {
        return this.val5;
    }

    public ClientboundCacheBrickPacket val6(final int val6) {
        if (val6 > 255L || val6 < 0L) {
            throw new IllegalArgumentException("Value " + val6 + " is out of bounds of allowed number range of 0 - 255");
        }
        this.val6 = val6;
        return this;
    }

    public int val6() {
        return this.val6;
    }

    public ClientboundCacheBrickPacket val7(final int val7) {
        if (val7 > 32767L || val7 < 0L) {
            throw new IllegalArgumentException("Value " + val7 + " is out of bounds of allowed number range of 0 - 32767");
        }
        this.val7 = val7;
        return this;
    }

    public int val7() {
        return this.val7;
    }

    public ClientboundCacheBrickPacket val8(final int val8) {
        if (val8 > 255L || val8 < 0L) {
            throw new IllegalArgumentException("Value " + val8 + " is out of bounds of allowed number range of 0 - 255");
        }
        this.val8 = val8;
        return this;
    }

    public int val8() {
        return this.val8;
    }

    public ClientboundCacheBrickPacket val9(final int val9) {
        if (val9 > 255L || val9 < 0L) {
            throw new IllegalArgumentException("Value " + val9 + " is out of bounds of allowed number range of 0 - 255");
        }
        this.val9 = val9;
        return this;
    }

    public int val9() {
        return this.val9;
    }

    public ClientboundCacheBrickPacket val10(final String val10) {
        this.val10 = val10;
        return this;
    }

    public String val10() {
        return this.val10;
    }

    public ClientboundCacheBrickPacket val11(final boolean val11) {
        this.val11 = val11;
        return this;
    }

    public boolean val11() {
        return this.val11;
    }

    public ClientboundCacheBrickPacket val12(final boolean val12) {
        this.val12 = val12;
        return this;
    }

    public boolean val12() {
        return this.val12;
    }

    public ClientboundCacheBrickPacket val13(final String val13) {
        this.val13 = val13;
        return this;
    }

    public String val13() {
        return this.val13;
    }

    @Override
    public int packetId() {
        return 21;
    }

    @Override
    public void write(final ByteBuf buffer) {
        buffer.writeIntLE(this.val);
        buffer.writeIntLE(this.val2);
        buffer.writeByte(this.val3 & 0xFF);
        buffer.writeByte(this.val4 & 0xFF);
        buffer.writeByte(this.val5 & 0xFF);
        buffer.writeByte(this.val6 & 0xFF);
        buffer.writeShortLE(this.val7 & 0xFFFF);
        buffer.writeByte(this.val8 & 0xFF);
        buffer.writeByte(this.val9 & 0xFF);
        if (this.val10.isEmpty()) {
            buffer.writeIntLE(0);
        } else {
            final byte[] bytes = this.val10.getBytes(StandardCharsets.UTF_16LE);
            buffer.writeIntLE(bytes.length);
            buffer.writeBytes(bytes);
        }
        buffer.writeBoolean(this.val11);
        buffer.writeBoolean(this.val12);
        if (this.val13.isEmpty()) {
            buffer.writeIntLE(0);
        } else {
            final byte[] bytes = this.val13.getBytes(StandardCharsets.UTF_16LE);
            buffer.writeIntLE(bytes.length);
            buffer.writeBytes(bytes);
        }
    }
}