package de.brickforceaurora.gameserver.net.protocol.clientbound;

import java.nio.charset.StandardCharsets;

import de.brickforceaurora.gameserver.net.protocol.IClientboundPacket;
import io.netty.buffer.ByteBuf;

public final class ClientboundTcChestPacket implements IClientboundPacket {

    private int val;
    private int val2;
    private int val3;
    private int val4;
    private int val5;
    private int val6;
    private int val7;
    private int val8;
    private String val9;
    private int val10;
    private String val11;
    private int val12;
    final String UnknownValue0 = "item.opt";
    private byte val13;

    public ClientboundTcChestPacket val(final int val) {
        this.val = val;
        return this;
    }

    public int val() {
        return this.val;
    }

    public ClientboundTcChestPacket val2(final int val2) {
        this.val2 = val2;
        return this;
    }

    public int val2() {
        return this.val2;
    }

    public ClientboundTcChestPacket val3(final int val3) {
        this.val3 = val3;
        return this;
    }

    public int val3() {
        return this.val3;
    }

    public ClientboundTcChestPacket val4(final int val4) {
        this.val4 = val4;
        return this;
    }

    public int val4() {
        return this.val4;
    }

    public ClientboundTcChestPacket val5(final int val5) {
        this.val5 = val5;
        return this;
    }

    public int val5() {
        return this.val5;
    }

    public ClientboundTcChestPacket val6(final int val6) {
        this.val6 = val6;
        return this;
    }

    public int val6() {
        return this.val6;
    }

    public ClientboundTcChestPacket val7(final int val7) {
        this.val7 = val7;
        return this;
    }

    public int val7() {
        return this.val7;
    }

    public ClientboundTcChestPacket val8(final int val8) {
        this.val8 = val8;
        return this;
    }

    public int val8() {
        return this.val8;
    }

    public ClientboundTcChestPacket val9(final String val9) {
        this.val9 = val9;
        return this;
    }

    public String val9() {
        return this.val9;
    }

    public ClientboundTcChestPacket val10(final int val10) {
        this.val10 = val10;
        return this;
    }

    public int val10() {
        return this.val10;
    }

    public ClientboundTcChestPacket val11(final String val11) {
        this.val11 = val11;
        return this;
    }

    public String val11() {
        return this.val11;
    }

    public ClientboundTcChestPacket val12(final int val12) {
        this.val12 = val12;
        return this;
    }

    public int val12() {
        return this.val12;
    }

    public ClientboundTcChestPacket val13(final byte val13) {
        this.val13 = val13;
        return this;
    }

    public byte val13() {
        return this.val13;
    }

    @Override
    public int packetId() {
        return 375;
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
        if (this.val9.isEmpty()) {
            buffer.writeIntLE(0);
        } else {
            final byte[] bytes = this.val9.getBytes(StandardCharsets.UTF_16LE);
            buffer.writeIntLE(bytes.length);
            buffer.writeBytes(bytes);
        }
        buffer.writeIntLE(this.val10);
        if (this.val11.isEmpty()) {
            buffer.writeIntLE(0);
        } else {
            final byte[] bytes = this.val11.getBytes(StandardCharsets.UTF_16LE);
            buffer.writeIntLE(bytes.length);
            buffer.writeBytes(bytes);
        }
        buffer.writeIntLE(this.val12);
        buffer.writeByte(this.val13);
    }
}