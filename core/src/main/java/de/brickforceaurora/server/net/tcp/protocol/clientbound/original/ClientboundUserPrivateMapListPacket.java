package de.brickforceaurora.server.net.tcp.protocol.clientbound.original;

import de.brickforceaurora.server.net.tcp.protocol.IClientboundPacket;
import de.brickforceaurora.server.net.tcp.protocol.PacketBuf;

public final class ClientboundUserPrivateMapListPacket implements IClientboundPacket {

    private int val;
    private int val2;
    private String val3;
    private int val4;
    private int val5;
    private byte val6;
    private byte val7;
    private byte val8;
    private byte val9;
    private byte val10;
    private byte val11;

    public ClientboundUserPrivateMapListPacket val(final int val) {
        this.val = val;
        return this;
    }

    public int val() {
        return this.val;
    }

    public ClientboundUserPrivateMapListPacket val2(final int val2) {
        if (val2 > 255L || val2 < 0L) {
            throw new IllegalArgumentException("Value " + val2 + " is out of bounds of allowed number range of 0 - 255");
        }
        this.val2 = val2;
        return this;
    }

    public int val2() {
        return this.val2;
    }

    public ClientboundUserPrivateMapListPacket val3(final String val3) {
        this.val3 = val3;
        return this;
    }

    public String val3() {
        return this.val3;
    }

    public ClientboundUserPrivateMapListPacket val4(final int val4) {
        this.val4 = val4;
        return this;
    }

    public int val4() {
        return this.val4;
    }

    public ClientboundUserPrivateMapListPacket val5(final int val5) {
        this.val5 = val5;
        return this;
    }

    public int val5() {
        return this.val5;
    }

    public ClientboundUserPrivateMapListPacket val6(final byte val6) {
        this.val6 = val6;
        return this;
    }

    public byte val6() {
        return this.val6;
    }

    public ClientboundUserPrivateMapListPacket val7(final byte val7) {
        this.val7 = val7;
        return this;
    }

    public byte val7() {
        return this.val7;
    }

    public ClientboundUserPrivateMapListPacket val8(final byte val8) {
        this.val8 = val8;
        return this;
    }

    public byte val8() {
        return this.val8;
    }

    public ClientboundUserPrivateMapListPacket val9(final byte val9) {
        this.val9 = val9;
        return this;
    }

    public byte val9() {
        return this.val9;
    }

    public ClientboundUserPrivateMapListPacket val10(final byte val10) {
        this.val10 = val10;
        return this;
    }

    public byte val10() {
        return this.val10;
    }

    public ClientboundUserPrivateMapListPacket val11(final byte val11) {
        this.val11 = val11;
        return this;
    }

    public byte val11() {
        return this.val11;
    }

    @Override
    public int packetId() {
        return 461;
    }

    @Override
    public void write(final PacketBuf buf) {
        buf.writeInt(this.val);
        buf.writeByte(this.val2);
        buf.writeString(this.val3);
        buf.writeInt(this.val4);
        buf.writeInt(this.val5);
        buf.writeByte(this.val6);
        buf.writeByte(this.val7);
        buf.writeByte(this.val8);
        buf.writeByte(this.val9);
        buf.writeByte(this.val10);
        buf.writeByte(this.val11);
    }
}