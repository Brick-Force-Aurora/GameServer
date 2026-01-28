package de.brickforceaurora.gameserver.net.protocol.clientbound;

import java.nio.charset.StandardCharsets;

import de.brickforceaurora.gameserver.net.protocol.IClientboundPacket;
import io.netty.buffer.ByteBuf;

public final class ClientboundClanDetailPacket implements IClientboundPacket {

    private int val;
    private String val2;
    private String val3;
    private int val4;
    private int val5;
    private int val6;
    private int val7;
    private int val8;
    private int val9;
    private int val10;
    private int val11;
    private int val12;
    private String val13;

    public ClientboundClanDetailPacket val(final int val) {
        this.val = val;
        return this;
    }

    public int val() {
        return this.val;
    }

    public ClientboundClanDetailPacket val2(final String val2) {
        this.val2 = val2;
        return this;
    }

    public String val2() {
        return this.val2;
    }

    public ClientboundClanDetailPacket val3(final String val3) {
        this.val3 = val3;
        return this;
    }

    public String val3() {
        return this.val3;
    }

    public ClientboundClanDetailPacket val4(final int val4) {
        this.val4 = val4;
        return this;
    }

    public int val4() {
        return this.val4;
    }

    public ClientboundClanDetailPacket val5(final int val5) {
        this.val5 = val5;
        return this;
    }

    public int val5() {
        return this.val5;
    }

    public ClientboundClanDetailPacket val6(final int val6) {
        this.val6 = val6;
        return this;
    }

    public int val6() {
        return this.val6;
    }

    public ClientboundClanDetailPacket val7(final int val7) {
        this.val7 = val7;
        return this;
    }

    public int val7() {
        return this.val7;
    }

    public ClientboundClanDetailPacket val8(final int val8) {
        this.val8 = val8;
        return this;
    }

    public int val8() {
        return this.val8;
    }

    public ClientboundClanDetailPacket val9(final int val9) {
        this.val9 = val9;
        return this;
    }

    public int val9() {
        return this.val9;
    }

    public ClientboundClanDetailPacket val10(final int val10) {
        this.val10 = val10;
        return this;
    }

    public int val10() {
        return this.val10;
    }

    public ClientboundClanDetailPacket val11(final int val11) {
        this.val11 = val11;
        return this;
    }

    public int val11() {
        return this.val11;
    }

    public ClientboundClanDetailPacket val12(final int val12) {
        this.val12 = val12;
        return this;
    }

    public int val12() {
        return this.val12;
    }

    public ClientboundClanDetailPacket val13(final String val13) {
        this.val13 = val13;
        return this;
    }

    public String val13() {
        return this.val13;
    }

    @Override
    public int packetId() {
        return 228;
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
        buffer.writeIntLE(this.val5);
        buffer.writeIntLE(this.val6);
        buffer.writeIntLE(this.val7);
        buffer.writeIntLE(this.val8);
        buffer.writeIntLE(this.val9);
        buffer.writeIntLE(this.val10);
        buffer.writeIntLE(this.val11);
        buffer.writeIntLE(this.val12);
        if (this.val13.isEmpty()) {
            buffer.writeIntLE(0);
        } else {
            final byte[] bytes = this.val13.getBytes(StandardCharsets.UTF_16LE);
            buffer.writeIntLE(bytes.length);
            buffer.writeBytes(bytes);
        }
    }
}