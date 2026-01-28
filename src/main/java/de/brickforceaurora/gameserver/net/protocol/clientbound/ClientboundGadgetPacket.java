package de.brickforceaurora.gameserver.net.protocol.clientbound;

import de.brickforceaurora.gameserver.net.protocol.IClientboundPacket;
import io.netty.buffer.ByteBuf;

public final class ClientboundGadgetPacket implements IClientboundPacket {

    private int val;
    private int Unnamed0;
    private int Unnamed1;
    private int val4;
    private float val5;
    private float val6;
    private float val7;
    private float val8;
    private float val9;
    private float val10;

    public ClientboundGadgetPacket val(final int val) {
        this.val = val;
        return this;
    }

    public int val() {
        return this.val;
    }

    public ClientboundGadgetPacket Unnamed0(final int Unnamed0) {
        this.Unnamed0 = Unnamed0;
        return this;
    }

    public int Unnamed0() {
        return this.Unnamed0;
    }

    public ClientboundGadgetPacket Unnamed1(final int Unnamed1) {
        this.Unnamed1 = Unnamed1;
        return this;
    }

    public int Unnamed1() {
        return this.Unnamed1;
    }

    public ClientboundGadgetPacket val4(final int val4) {
        this.val4 = val4;
        return this;
    }

    public int val4() {
        return this.val4;
    }

    public ClientboundGadgetPacket val5(final float val5) {
        this.val5 = val5;
        return this;
    }

    public float val5() {
        return this.val5;
    }

    public ClientboundGadgetPacket val6(final float val6) {
        this.val6 = val6;
        return this;
    }

    public float val6() {
        return this.val6;
    }

    public ClientboundGadgetPacket val7(final float val7) {
        this.val7 = val7;
        return this;
    }

    public float val7() {
        return this.val7;
    }

    public ClientboundGadgetPacket val8(final float val8) {
        this.val8 = val8;
        return this;
    }

    public float val8() {
        return this.val8;
    }

    public ClientboundGadgetPacket val9(final float val9) {
        this.val9 = val9;
        return this;
    }

    public float val9() {
        return this.val9;
    }

    public ClientboundGadgetPacket val10(final float val10) {
        this.val10 = val10;
        return this;
    }

    public float val10() {
        return this.val10;
    }

    @Override
    public int packetId() {
        return 401;
    }

    @Override
    public void write(final ByteBuf buffer) {
        buffer.writeIntLE(this.val);
        buffer.writeIntLE(this.Unnamed0);
        buffer.writeIntLE(this.Unnamed1);
        buffer.writeIntLE(this.val4);
        buffer.writeFloatLE(this.val5);
        buffer.writeFloatLE(this.val6);
        buffer.writeFloatLE(this.val7);
        buffer.writeFloatLE(this.val8);
        buffer.writeFloatLE(this.val9);
        buffer.writeFloatLE(this.val10);
    }
}