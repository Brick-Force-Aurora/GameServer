package de.brickforceaurora.gameserver.net.protocol.clientbound;

import java.nio.charset.StandardCharsets;

import de.brickforceaurora.gameserver.net.protocol.IClientboundPacket;
import io.netty.buffer.ByteBuf;

public final class ClientboundRoomConfigPacket implements IClientboundPacket {

    private int val;
    private String val2;
    private int val3;
    private int val4;
    private int val5;
    private boolean val6;
    private boolean val7;
    private boolean val8;
    private String val9;
    private int val10;
    private int val11;
    private boolean val12;
    private boolean val13;

    public ClientboundRoomConfigPacket val(final int val) {
        this.val = val;
        return this;
    }

    public int val() {
        return this.val;
    }

    public ClientboundRoomConfigPacket val2(final String val2) {
        this.val2 = val2;
        return this;
    }

    public String val2() {
        return this.val2;
    }

    public ClientboundRoomConfigPacket val3(final int val3) {
        this.val3 = val3;
        return this;
    }

    public int val3() {
        return this.val3;
    }

    public ClientboundRoomConfigPacket val4(final int val4) {
        this.val4 = val4;
        return this;
    }

    public int val4() {
        return this.val4;
    }

    public ClientboundRoomConfigPacket val5(final int val5) {
        this.val5 = val5;
        return this;
    }

    public int val5() {
        return this.val5;
    }

    public ClientboundRoomConfigPacket val6(final boolean val6) {
        this.val6 = val6;
        return this;
    }

    public boolean val6() {
        return this.val6;
    }

    public ClientboundRoomConfigPacket val7(final boolean val7) {
        this.val7 = val7;
        return this;
    }

    public boolean val7() {
        return this.val7;
    }

    public ClientboundRoomConfigPacket val8(final boolean val8) {
        this.val8 = val8;
        return this;
    }

    public boolean val8() {
        return this.val8;
    }

    public ClientboundRoomConfigPacket val9(final String val9) {
        this.val9 = val9;
        return this;
    }

    public String val9() {
        return this.val9;
    }

    public ClientboundRoomConfigPacket val10(final int val10) {
        if (val10 > 255L || val10 < 0L) {
            throw new IllegalArgumentException("Value " + val10 + " is out of bounds of allowed number range of 0 - 255");
        }
        this.val10 = val10;
        return this;
    }

    public int val10() {
        return this.val10;
    }

    public ClientboundRoomConfigPacket val11(final int val11) {
        this.val11 = val11;
        return this;
    }

    public int val11() {
        return this.val11;
    }

    public ClientboundRoomConfigPacket val12(final boolean val12) {
        this.val12 = val12;
        return this;
    }

    public boolean val12() {
        return this.val12;
    }

    public ClientboundRoomConfigPacket val13(final boolean val13) {
        this.val13 = val13;
        return this;
    }

    public boolean val13() {
        return this.val13;
    }

    @Override
    public int packetId() {
        return 92;
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
        buffer.writeIntLE(this.val3);
        buffer.writeIntLE(this.val4);
        buffer.writeIntLE(this.val5);
        buffer.writeBoolean(this.val6);
        buffer.writeBoolean(this.val7);
        buffer.writeBoolean(this.val8);
        if (this.val9.isEmpty()) {
            buffer.writeIntLE(0);
        } else {
            final byte[] bytes = this.val9.getBytes(StandardCharsets.UTF_16LE);
            buffer.writeIntLE(bytes.length);
            buffer.writeBytes(bytes);
        }
        buffer.writeByte(this.val10 & 0xFF);
        buffer.writeIntLE(this.val11);
        buffer.writeBoolean(this.val12);
        buffer.writeBoolean(this.val13);
    }
}