package de.brickforceaurora.server.net.protocol.clientbound.original;

import de.brickforceaurora.server.net.protocol.IClientboundPacket;
import de.brickforceaurora.server.net.protocol.PacketBuf;

public final class ClientboundDropitemQuickJoinUserPacket implements IClientboundPacket {

    private int val;
    private int val2;
    private String val3;
    private int val4;
    private int val5;
    private float val6;
    private float val7;
    private float val8;

    public ClientboundDropitemQuickJoinUserPacket val(final int val) {
        this.val = val;
        return this;
    }

    public int val() {
        return this.val;
    }

    public ClientboundDropitemQuickJoinUserPacket val2(final int val2) {
        this.val2 = val2;
        return this;
    }

    public int val2() {
        return this.val2;
    }

    public ClientboundDropitemQuickJoinUserPacket val3(final String val3) {
        this.val3 = val3;
        return this;
    }

    public String val3() {
        return this.val3;
    }

    public ClientboundDropitemQuickJoinUserPacket val4(final int val4) {
        this.val4 = val4;
        return this;
    }

    public int val4() {
        return this.val4;
    }

    public ClientboundDropitemQuickJoinUserPacket val5(final int val5) {
        this.val5 = val5;
        return this;
    }

    public int val5() {
        return this.val5;
    }

    public ClientboundDropitemQuickJoinUserPacket val6(final float val6) {
        this.val6 = val6;
        return this;
    }

    public float val6() {
        return this.val6;
    }

    public ClientboundDropitemQuickJoinUserPacket val7(final float val7) {
        this.val7 = val7;
        return this;
    }

    public float val7() {
        return this.val7;
    }

    public ClientboundDropitemQuickJoinUserPacket val8(final float val8) {
        this.val8 = val8;
        return this;
    }

    public float val8() {
        return this.val8;
    }

    @Override
    public int packetId() {
        return 531;
    }

    @Override
    public void write(final PacketBuf buf) {
        buf.writeInt(this.val);
        buf.writeInt(this.val2);
        buf.writeString(this.val3);
        buf.writeInt(this.val4);
        buf.writeInt(this.val5);
        buf.writeFloat(this.val6);
        buf.writeFloat(this.val7);
        buf.writeFloat(this.val8);
    }
}