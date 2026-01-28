package de.brickforceaurora.gameserver.net.protocol.clientbound;

import java.nio.charset.StandardCharsets;

import de.brickforceaurora.gameserver.net.protocol.IClientboundPacket;
import io.netty.buffer.ByteBuf;

public final class ClientboundClanListPacket implements IClientboundPacket {

    private int val;
    private int val2;
    private int val3;
    private String val4;
    private int val5;
    private int val6;
    private int val7;
    private int val8;
    private int val9;
    private int val10;
    private int val11;
    private int val12;
    private int val13;
    private int val14;
    private int val15;
    private int val16;
    private int val17;
    private int val18;
    private String val19;

    public ClientboundClanListPacket val(final int val) {
        this.val = val;
        return this;
    }

    public int val() {
        return this.val;
    }

    public ClientboundClanListPacket val2(final int val2) {
        this.val2 = val2;
        return this;
    }

    public int val2() {
        return this.val2;
    }

    public ClientboundClanListPacket val3(final int val3) {
        this.val3 = val3;
        return this;
    }

    public int val3() {
        return this.val3;
    }

    public ClientboundClanListPacket val4(final String val4) {
        this.val4 = val4;
        return this;
    }

    public String val4() {
        return this.val4;
    }

    public ClientboundClanListPacket val5(final int val5) {
        this.val5 = val5;
        return this;
    }

    public int val5() {
        return this.val5;
    }

    public ClientboundClanListPacket val6(final int val6) {
        this.val6 = val6;
        return this;
    }

    public int val6() {
        return this.val6;
    }

    public ClientboundClanListPacket val7(final int val7) {
        this.val7 = val7;
        return this;
    }

    public int val7() {
        return this.val7;
    }

    public ClientboundClanListPacket val8(final int val8) {
        this.val8 = val8;
        return this;
    }

    public int val8() {
        return this.val8;
    }

    public ClientboundClanListPacket val9(final int val9) {
        this.val9 = val9;
        return this;
    }

    public int val9() {
        return this.val9;
    }

    public ClientboundClanListPacket val10(final int val10) {
        this.val10 = val10;
        return this;
    }

    public int val10() {
        return this.val10;
    }

    public ClientboundClanListPacket val11(final int val11) {
        this.val11 = val11;
        return this;
    }

    public int val11() {
        return this.val11;
    }

    public ClientboundClanListPacket val12(final int val12) {
        this.val12 = val12;
        return this;
    }

    public int val12() {
        return this.val12;
    }

    public ClientboundClanListPacket val13(final int val13) {
        this.val13 = val13;
        return this;
    }

    public int val13() {
        return this.val13;
    }

    public ClientboundClanListPacket val14(final int val14) {
        this.val14 = val14;
        return this;
    }

    public int val14() {
        return this.val14;
    }

    public ClientboundClanListPacket val15(final int val15) {
        this.val15 = val15;
        return this;
    }

    public int val15() {
        return this.val15;
    }

    public ClientboundClanListPacket val16(final int val16) {
        this.val16 = val16;
        return this;
    }

    public int val16() {
        return this.val16;
    }

    public ClientboundClanListPacket val17(final int val17) {
        this.val17 = val17;
        return this;
    }

    public int val17() {
        return this.val17;
    }

    public ClientboundClanListPacket val18(final int val18) {
        this.val18 = val18;
        return this;
    }

    public int val18() {
        return this.val18;
    }

    public ClientboundClanListPacket val19(final String val19) {
        this.val19 = val19;
        return this;
    }

    public String val19() {
        return this.val19;
    }

    @Override
    public int packetId() {
        return 298;
    }

    @Override
    public void write(final ByteBuf buffer) {
        buffer.writeIntLE(this.val);
        buffer.writeIntLE(this.val2);
        buffer.writeIntLE(this.val3);
        if (this.val4.isEmpty()) {
            buffer.writeIntLE(0);
        } else {
            final byte[] bytes = this.val4.getBytes(StandardCharsets.UTF_16LE);
            buffer.writeIntLE(bytes.length);
            buffer.writeBytes(bytes);
        }
        buffer.writeIntLE(this.val5);
        buffer.writeIntLE(this.val6);
        buffer.writeIntLE(this.val7);
        buffer.writeIntLE(this.val8);
        buffer.writeIntLE(this.val9);
        buffer.writeIntLE(this.val10);
        buffer.writeIntLE(this.val11);
        buffer.writeIntLE(this.val12);
        buffer.writeIntLE(this.val13);
        buffer.writeIntLE(this.val14);
        buffer.writeIntLE(this.val15);
        buffer.writeIntLE(this.val16);
        buffer.writeIntLE(this.val17);
        buffer.writeIntLE(this.val18);
        if (this.val19.isEmpty()) {
            buffer.writeIntLE(0);
        } else {
            final byte[] bytes = this.val19.getBytes(StandardCharsets.UTF_16LE);
            buffer.writeIntLE(bytes.length);
            buffer.writeBytes(bytes);
        }
    }
}