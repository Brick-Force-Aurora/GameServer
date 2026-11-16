package de.brickforceaurora.server.net.protocol.clientbound.original;

import de.brickforceaurora.server.net.protocol.IClientboundPacket;
import de.brickforceaurora.server.net.protocol.PacketBuf;

public final class ClientboundTcReceivePrizePacket implements IClientboundPacket {

    private int val;
    private long val2;
    private String val3;
    private byte val4;
    private int val5;
    private int val6;
    private int val7;
    private boolean val8;
    private int val9;

    public ClientboundTcReceivePrizePacket val(final int val) {
        this.val = val;
        return this;
    }

    public int val() {
        return this.val;
    }

    public ClientboundTcReceivePrizePacket val2(final long val2) {
        this.val2 = val2;
        return this;
    }

    public long val2() {
        return this.val2;
    }

    public ClientboundTcReceivePrizePacket val3(final String val3) {
        this.val3 = val3;
        return this;
    }

    public String val3() {
        return this.val3;
    }

    public ClientboundTcReceivePrizePacket val4(final byte val4) {
        this.val4 = val4;
        return this;
    }

    public byte val4() {
        return this.val4;
    }

    public ClientboundTcReceivePrizePacket val5(final int val5) {
        this.val5 = val5;
        return this;
    }

    public int val5() {
        return this.val5;
    }

    public ClientboundTcReceivePrizePacket val6(final int val6) {
        this.val6 = val6;
        return this;
    }

    public int val6() {
        return this.val6;
    }

    public ClientboundTcReceivePrizePacket val7(final int val7) {
        this.val7 = val7;
        return this;
    }

    public int val7() {
        return this.val7;
    }

    public ClientboundTcReceivePrizePacket val8(final boolean val8) {
        this.val8 = val8;
        return this;
    }

    public boolean val8() {
        return this.val8;
    }

    public ClientboundTcReceivePrizePacket val9(final int val9) {
        this.val9 = val9;
        return this;
    }

    public int val9() {
        return this.val9;
    }

    @Override
    public int packetId() {
        return 380;
    }

    @Override
    public void write(final PacketBuf buf) {
        buf.writeInt(this.val);
        buf.writeLong(this.val2);
        buf.writeString(this.val3);
        buf.writeByte(this.val4);
        buf.writeInt(this.val5);
        buf.writeInt(this.val6);
        buf.writeInt(this.val7);
        buf.writeBoolean(this.val8);
        buf.writeInt(this.val9);
    }
}