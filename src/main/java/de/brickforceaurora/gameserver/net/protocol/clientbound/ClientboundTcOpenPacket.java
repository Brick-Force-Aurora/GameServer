package de.brickforceaurora.gameserver.net.protocol.clientbound;

import java.nio.charset.StandardCharsets;

import de.brickforceaurora.gameserver.net.protocol.IClientboundPacket;
import io.netty.buffer.ByteBuf;

public final class ClientboundTcOpenPacket implements IClientboundPacket {

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
    private int val11;
    private String val12;
    private int val13;
    final String UnknownValue0 = "item.opt";
    private byte val14;

    public ClientboundTcOpenPacket val(final int val) {
        this.val = val;
        return this;
    }

    public int val() {
        return this.val;
    }

    public ClientboundTcOpenPacket val2(final int val2) {
        this.val2 = val2;
        return this;
    }

    public int val2() {
        return this.val2;
    }

    public ClientboundTcOpenPacket val3(final int val3) {
        this.val3 = val3;
        return this;
    }

    public int val3() {
        return this.val3;
    }

    public ClientboundTcOpenPacket val4(final int val4) {
        this.val4 = val4;
        return this;
    }

    public int val4() {
        return this.val4;
    }

    public ClientboundTcOpenPacket val5(final int val5) {
        this.val5 = val5;
        return this;
    }

    public int val5() {
        return this.val5;
    }

    public ClientboundTcOpenPacket val6(final int val6) {
        this.val6 = val6;
        return this;
    }

    public int val6() {
        return this.val6;
    }

    public ClientboundTcOpenPacket val7(final int val7) {
        this.val7 = val7;
        return this;
    }

    public int val7() {
        return this.val7;
    }

    public ClientboundTcOpenPacket val8(final int val8) {
        this.val8 = val8;
        return this;
    }

    public int val8() {
        return this.val8;
    }

    public ClientboundTcOpenPacket val9(final int val9) {
        this.val9 = val9;
        return this;
    }

    public int val9() {
        return this.val9;
    }

    public ClientboundTcOpenPacket val10(final String val10) {
        this.val10 = val10;
        return this;
    }

    public String val10() {
        return this.val10;
    }

    public ClientboundTcOpenPacket val11(final int val11) {
        this.val11 = val11;
        return this;
    }

    public int val11() {
        return this.val11;
    }

    public ClientboundTcOpenPacket val12(final String val12) {
        this.val12 = val12;
        return this;
    }

    public String val12() {
        return this.val12;
    }

    public ClientboundTcOpenPacket val13(final int val13) {
        this.val13 = val13;
        return this;
    }

    public int val13() {
        return this.val13;
    }

    public ClientboundTcOpenPacket val14(final byte val14) {
        this.val14 = val14;
        return this;
    }

    public byte val14() {
        return this.val14;
    }

    @Override
    public int packetId() {
        return 370;
    }

    @Override
    public void write(final ByteBuf buffer) {
        buffer.writeIntLE(this.val);
        buffer.writeIntLE(this.val2);
        buffer.writeIntLE(this.val3);
        buffer.writeIntLE(this.val4);
        buffer.writeIntLE(this.val5);
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
        buffer.writeIntLE(this.val11);
        if (this.val12.isEmpty()) {
            buffer.writeIntLE(0);
        } else {
            final byte[] bytes = this.val12.getBytes(StandardCharsets.UTF_16LE);
            buffer.writeIntLE(bytes.length);
            buffer.writeBytes(bytes);
        }
        buffer.writeIntLE(this.val13);
        buffer.writeByte(this.val14);
    }
}