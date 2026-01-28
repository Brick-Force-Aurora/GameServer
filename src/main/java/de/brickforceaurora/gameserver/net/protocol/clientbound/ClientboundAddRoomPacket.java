package de.brickforceaurora.gameserver.net.protocol.clientbound;

import java.nio.charset.StandardCharsets;

import de.brickforceaurora.gameserver.net.protocol.IClientboundPacket;
import io.netty.buffer.ByteBuf;

public final class ClientboundAddRoomPacket implements IClientboundPacket {

    private int val;
    private int val2;
    private String val3;
    private boolean val4;
    private int val5;
    private int val6;
    private int val7;
    private int val8;
    private String val9;
    private int val10;
    private int val11;
    private int val12;
    private int val13;
    private int val14;
    private int val15;
    private int val16;
    private boolean val17;
    private boolean val18;
    private boolean val19;
    private int val20;
    private int val21;

    public ClientboundAddRoomPacket val(final int val) {
        this.val = val;
        return this;
    }

    public int val() {
        return this.val;
    }

    public ClientboundAddRoomPacket val2(final int val2) {
        this.val2 = val2;
        return this;
    }

    public int val2() {
        return this.val2;
    }

    public ClientboundAddRoomPacket val3(final String val3) {
        this.val3 = val3;
        return this;
    }

    public String val3() {
        return this.val3;
    }

    public ClientboundAddRoomPacket val4(final boolean val4) {
        this.val4 = val4;
        return this;
    }

    public boolean val4() {
        return this.val4;
    }

    public ClientboundAddRoomPacket val5(final int val5) {
        this.val5 = val5;
        return this;
    }

    public int val5() {
        return this.val5;
    }

    public ClientboundAddRoomPacket val6(final int val6) {
        this.val6 = val6;
        return this;
    }

    public int val6() {
        return this.val6;
    }

    public ClientboundAddRoomPacket val7(final int val7) {
        this.val7 = val7;
        return this;
    }

    public int val7() {
        return this.val7;
    }

    public ClientboundAddRoomPacket val8(final int val8) {
        this.val8 = val8;
        return this;
    }

    public int val8() {
        return this.val8;
    }

    public ClientboundAddRoomPacket val9(final String val9) {
        this.val9 = val9;
        return this;
    }

    public String val9() {
        return this.val9;
    }

    public ClientboundAddRoomPacket val10(final int val10) {
        this.val10 = val10;
        return this;
    }

    public int val10() {
        return this.val10;
    }

    public ClientboundAddRoomPacket val11(final int val11) {
        this.val11 = val11;
        return this;
    }

    public int val11() {
        return this.val11;
    }

    public ClientboundAddRoomPacket val12(final int val12) {
        this.val12 = val12;
        return this;
    }

    public int val12() {
        return this.val12;
    }

    public ClientboundAddRoomPacket val13(final int val13) {
        this.val13 = val13;
        return this;
    }

    public int val13() {
        return this.val13;
    }

    public ClientboundAddRoomPacket val14(final int val14) {
        this.val14 = val14;
        return this;
    }

    public int val14() {
        return this.val14;
    }

    public ClientboundAddRoomPacket val15(final int val15) {
        this.val15 = val15;
        return this;
    }

    public int val15() {
        return this.val15;
    }

    public ClientboundAddRoomPacket val16(final int val16) {
        this.val16 = val16;
        return this;
    }

    public int val16() {
        return this.val16;
    }

    public ClientboundAddRoomPacket val17(final boolean val17) {
        this.val17 = val17;
        return this;
    }

    public boolean val17() {
        return this.val17;
    }

    public ClientboundAddRoomPacket val18(final boolean val18) {
        this.val18 = val18;
        return this;
    }

    public boolean val18() {
        return this.val18;
    }

    public ClientboundAddRoomPacket val19(final boolean val19) {
        this.val19 = val19;
        return this;
    }

    public boolean val19() {
        return this.val19;
    }

    public ClientboundAddRoomPacket val20(final int val20) {
        this.val20 = val20;
        return this;
    }

    public int val20() {
        return this.val20;
    }

    public ClientboundAddRoomPacket val21(final int val21) {
        this.val21 = val21;
        return this;
    }

    public int val21() {
        return this.val21;
    }

    @Override
    public int packetId() {
        return 5;
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
        buffer.writeBoolean(this.val4);
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
        buffer.writeIntLE(this.val11);
        buffer.writeIntLE(this.val12);
        buffer.writeIntLE(this.val13);
        buffer.writeIntLE(this.val14);
        buffer.writeIntLE(this.val15);
        buffer.writeIntLE(this.val16);
        buffer.writeBoolean(this.val17);
        buffer.writeBoolean(this.val18);
        buffer.writeBoolean(this.val19);
        buffer.writeIntLE(this.val20);
        buffer.writeIntLE(this.val21);
    }
}