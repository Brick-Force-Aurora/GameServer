package de.brickforceaurora.server.net.tcp.protocol.clientbound.original;

import de.brickforceaurora.server.net.tcp.protocol.IClientboundPacket;
import de.brickforceaurora.server.net.tcp.protocol.PacketBuf;

public final class ClientboundMoreCommentPacket implements IClientboundPacket {

    private int Unnamed0;
    private int val2;
    private int val3;
    private String val4;
    private String val5;
    private int val6;

    public ClientboundMoreCommentPacket Unnamed0(final int Unnamed0) {
        this.Unnamed0 = Unnamed0;
        return this;
    }

    public int Unnamed0() {
        return this.Unnamed0;
    }

    public ClientboundMoreCommentPacket val2(final int val2) {
        this.val2 = val2;
        return this;
    }

    public int val2() {
        return this.val2;
    }

    public ClientboundMoreCommentPacket val3(final int val3) {
        this.val3 = val3;
        return this;
    }

    public int val3() {
        return this.val3;
    }

    public ClientboundMoreCommentPacket val4(final String val4) {
        this.val4 = val4;
        return this;
    }

    public String val4() {
        return this.val4;
    }

    public ClientboundMoreCommentPacket val5(final String val5) {
        this.val5 = val5;
        return this;
    }

    public String val5() {
        return this.val5;
    }

    public ClientboundMoreCommentPacket val6(final int val6) {
        if (val6 > 255L || val6 < 0L) {
            throw new IllegalArgumentException("Value " + val6 + " is out of bounds of allowed number range of 0 - 255");
        }
        this.val6 = val6;
        return this;
    }

    public int val6() {
        return this.val6;
    }

    @Override
    public int packetId() {
        return 440;
    }

    @Override
    public void write(final PacketBuf buf) {
        buf.writeInt(this.Unnamed0);
        buf.writeInt(this.val2);
        buf.writeInt(this.val3);
        buf.writeString(this.val4);
        buf.writeString(this.val5);
        buf.writeByte(this.val6);
    }
}