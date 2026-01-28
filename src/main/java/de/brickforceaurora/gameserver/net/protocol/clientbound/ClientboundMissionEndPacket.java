package de.brickforceaurora.gameserver.net.protocol.clientbound;

import java.nio.charset.StandardCharsets;

import de.brickforceaurora.gameserver.net.protocol.IClientboundPacket;
import io.netty.buffer.ByteBuf;

public final class ClientboundMissionEndPacket implements IClientboundPacket {

    private byte val;
    private byte val2;
    private int val3;
    private boolean val4;
    private int val5;
    private String val6;
    private int val7;
    private int val8;
    private int val9;
    private int val10;
    private int val11;
    private int val12;
    private int val13;
    private int val14;
    private int val15;
    private long val16;

    public ClientboundMissionEndPacket val(final byte val) {
        this.val = val;
        return this;
    }

    public byte val() {
        return this.val;
    }

    public ClientboundMissionEndPacket val2(final byte val2) {
        this.val2 = val2;
        return this;
    }

    public byte val2() {
        return this.val2;
    }

    public ClientboundMissionEndPacket val3(final int val3) {
        this.val3 = val3;
        return this;
    }

    public int val3() {
        return this.val3;
    }

    public ClientboundMissionEndPacket val4(final boolean val4) {
        this.val4 = val4;
        return this;
    }

    public boolean val4() {
        return this.val4;
    }

    public ClientboundMissionEndPacket val5(final int val5) {
        this.val5 = val5;
        return this;
    }

    public int val5() {
        return this.val5;
    }

    public ClientboundMissionEndPacket val6(final String val6) {
        this.val6 = val6;
        return this;
    }

    public String val6() {
        return this.val6;
    }

    public ClientboundMissionEndPacket val7(final int val7) {
        this.val7 = val7;
        return this;
    }

    public int val7() {
        return this.val7;
    }

    public ClientboundMissionEndPacket val8(final int val8) {
        this.val8 = val8;
        return this;
    }

    public int val8() {
        return this.val8;
    }

    public ClientboundMissionEndPacket val9(final int val9) {
        this.val9 = val9;
        return this;
    }

    public int val9() {
        return this.val9;
    }

    public ClientboundMissionEndPacket val10(final int val10) {
        this.val10 = val10;
        return this;
    }

    public int val10() {
        return this.val10;
    }

    public ClientboundMissionEndPacket val11(final int val11) {
        this.val11 = val11;
        return this;
    }

    public int val11() {
        return this.val11;
    }

    public ClientboundMissionEndPacket val12(final int val12) {
        this.val12 = val12;
        return this;
    }

    public int val12() {
        return this.val12;
    }

    public ClientboundMissionEndPacket val13(final int val13) {
        this.val13 = val13;
        return this;
    }

    public int val13() {
        return this.val13;
    }

    public ClientboundMissionEndPacket val14(final int val14) {
        this.val14 = val14;
        return this;
    }

    public int val14() {
        return this.val14;
    }

    public ClientboundMissionEndPacket val15(final int val15) {
        this.val15 = val15;
        return this;
    }

    public int val15() {
        return this.val15;
    }

    public ClientboundMissionEndPacket val16(final long val16) {
        this.val16 = val16;
        return this;
    }

    public long val16() {
        return this.val16;
    }

    @Override
    public int packetId() {
        return 182;
    }

    @Override
    public void write(final ByteBuf buffer) {
        buffer.writeByte(this.val);
        buffer.writeByte(this.val2);
        buffer.writeIntLE(this.val3);
        buffer.writeBoolean(this.val4);
        buffer.writeIntLE(this.val5);
        if (this.val6.isEmpty()) {
            buffer.writeIntLE(0);
        } else {
            final byte[] bytes = this.val6.getBytes(StandardCharsets.UTF_16LE);
            buffer.writeIntLE(bytes.length);
            buffer.writeBytes(bytes);
        }
        buffer.writeIntLE(this.val7);
        buffer.writeIntLE(this.val8);
        buffer.writeIntLE(this.val9);
        buffer.writeIntLE(this.val10);
        buffer.writeIntLE(this.val11);
        buffer.writeIntLE(this.val12);
        buffer.writeIntLE(this.val13);
        buffer.writeIntLE(this.val14);
        buffer.writeIntLE(this.val15);
        buffer.writeLongLE(this.val16);
    }
}