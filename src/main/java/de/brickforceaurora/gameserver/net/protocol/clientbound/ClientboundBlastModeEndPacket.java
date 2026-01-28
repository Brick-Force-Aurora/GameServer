package de.brickforceaurora.gameserver.net.protocol.clientbound;

import java.nio.charset.StandardCharsets;

import de.brickforceaurora.gameserver.net.protocol.IClientboundPacket;
import io.netty.buffer.ByteBuf;

public final class ClientboundBlastModeEndPacket implements IClientboundPacket {

    private byte val;
    private int val2;
    private int val3;
    private int val4;
    private int val5;
    private int val6;
    private int val7;
    private int val8;
    private int val9;
    private int val10;
    private boolean val11;
    private int val12;
    private String val13;
    private int val14;
    private int val15;
    private int val16;
    private int val17;
    private int val18;
    private int val19;
    private int val20;
    private int val21;
    private int val22;
    private long val23;

    public ClientboundBlastModeEndPacket val(final byte val) {
        this.val = val;
        return this;
    }

    public byte val() {
        return this.val;
    }

    public ClientboundBlastModeEndPacket val2(final int val2) {
        this.val2 = val2;
        return this;
    }

    public int val2() {
        return this.val2;
    }

    public ClientboundBlastModeEndPacket val3(final int val3) {
        this.val3 = val3;
        return this;
    }

    public int val3() {
        return this.val3;
    }

    public ClientboundBlastModeEndPacket val4(final int val4) {
        this.val4 = val4;
        return this;
    }

    public int val4() {
        return this.val4;
    }

    public ClientboundBlastModeEndPacket val5(final int val5) {
        this.val5 = val5;
        return this;
    }

    public int val5() {
        return this.val5;
    }

    public ClientboundBlastModeEndPacket val6(final int val6) {
        this.val6 = val6;
        return this;
    }

    public int val6() {
        return this.val6;
    }

    public ClientboundBlastModeEndPacket val7(final int val7) {
        this.val7 = val7;
        return this;
    }

    public int val7() {
        return this.val7;
    }

    public ClientboundBlastModeEndPacket val8(final int val8) {
        this.val8 = val8;
        return this;
    }

    public int val8() {
        return this.val8;
    }

    public ClientboundBlastModeEndPacket val9(final int val9) {
        this.val9 = val9;
        return this;
    }

    public int val9() {
        return this.val9;
    }

    public ClientboundBlastModeEndPacket val10(final int val10) {
        this.val10 = val10;
        return this;
    }

    public int val10() {
        return this.val10;
    }

    public ClientboundBlastModeEndPacket val11(final boolean val11) {
        this.val11 = val11;
        return this;
    }

    public boolean val11() {
        return this.val11;
    }

    public ClientboundBlastModeEndPacket val12(final int val12) {
        this.val12 = val12;
        return this;
    }

    public int val12() {
        return this.val12;
    }

    public ClientboundBlastModeEndPacket val13(final String val13) {
        this.val13 = val13;
        return this;
    }

    public String val13() {
        return this.val13;
    }

    public ClientboundBlastModeEndPacket val14(final int val14) {
        this.val14 = val14;
        return this;
    }

    public int val14() {
        return this.val14;
    }

    public ClientboundBlastModeEndPacket val15(final int val15) {
        this.val15 = val15;
        return this;
    }

    public int val15() {
        return this.val15;
    }

    public ClientboundBlastModeEndPacket val16(final int val16) {
        this.val16 = val16;
        return this;
    }

    public int val16() {
        return this.val16;
    }

    public ClientboundBlastModeEndPacket val17(final int val17) {
        this.val17 = val17;
        return this;
    }

    public int val17() {
        return this.val17;
    }

    public ClientboundBlastModeEndPacket val18(final int val18) {
        this.val18 = val18;
        return this;
    }

    public int val18() {
        return this.val18;
    }

    public ClientboundBlastModeEndPacket val19(final int val19) {
        this.val19 = val19;
        return this;
    }

    public int val19() {
        return this.val19;
    }

    public ClientboundBlastModeEndPacket val20(final int val20) {
        this.val20 = val20;
        return this;
    }

    public int val20() {
        return this.val20;
    }

    public ClientboundBlastModeEndPacket val21(final int val21) {
        this.val21 = val21;
        return this;
    }

    public int val21() {
        return this.val21;
    }

    public ClientboundBlastModeEndPacket val22(final int val22) {
        this.val22 = val22;
        return this;
    }

    public int val22() {
        return this.val22;
    }

    public ClientboundBlastModeEndPacket val23(final long val23) {
        this.val23 = val23;
        return this;
    }

    public long val23() {
        return this.val23;
    }

    @Override
    public int packetId() {
        return 291;
    }

    @Override
    public void write(final ByteBuf buffer) {
        buffer.writeByte(this.val);
        buffer.writeIntLE(this.val2);
        buffer.writeIntLE(this.val3);
        buffer.writeIntLE(this.val4);
        buffer.writeIntLE(this.val5);
        buffer.writeIntLE(this.val6);
        buffer.writeIntLE(this.val7);
        buffer.writeIntLE(this.val8);
        buffer.writeIntLE(this.val9);
        buffer.writeIntLE(this.val10);
        buffer.writeBoolean(this.val11);
        buffer.writeIntLE(this.val12);
        if (this.val13.isEmpty()) {
            buffer.writeIntLE(0);
        } else {
            final byte[] bytes = this.val13.getBytes(StandardCharsets.UTF_16LE);
            buffer.writeIntLE(bytes.length);
            buffer.writeBytes(bytes);
        }
        buffer.writeIntLE(this.val14);
        buffer.writeIntLE(this.val15);
        buffer.writeIntLE(this.val16);
        buffer.writeIntLE(this.val17);
        buffer.writeIntLE(this.val18);
        buffer.writeIntLE(this.val19);
        buffer.writeIntLE(this.val20);
        buffer.writeIntLE(this.val21);
        buffer.writeIntLE(this.val22);
        buffer.writeLongLE(this.val23);
    }
}