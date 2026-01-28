package de.brickforceaurora.gameserver.net.protocol.clientbound;

import de.brickforceaurora.gameserver.net.protocol.IClientboundPacket;
import io.netty.buffer.ByteBuf;

public final class ClientboundBmInstallBombPacket implements IClientboundPacket {

    private int val;
    private int val2;
    private float val3;
    private float val4;
    private float val5;
    private float val6;
    private float val7;
    private float val8;

    public ClientboundBmInstallBombPacket val(final int val) {
        this.val = val;
        return this;
    }

    public int val() {
        return this.val;
    }

    public ClientboundBmInstallBombPacket val2(final int val2) {
        this.val2 = val2;
        return this;
    }

    public int val2() {
        return this.val2;
    }

    public ClientboundBmInstallBombPacket val3(final float val3) {
        this.val3 = val3;
        return this;
    }

    public float val3() {
        return this.val3;
    }

    public ClientboundBmInstallBombPacket val4(final float val4) {
        this.val4 = val4;
        return this;
    }

    public float val4() {
        return this.val4;
    }

    public ClientboundBmInstallBombPacket val5(final float val5) {
        this.val5 = val5;
        return this;
    }

    public float val5() {
        return this.val5;
    }

    public ClientboundBmInstallBombPacket val6(final float val6) {
        this.val6 = val6;
        return this;
    }

    public float val6() {
        return this.val6;
    }

    public ClientboundBmInstallBombPacket val7(final float val7) {
        this.val7 = val7;
        return this;
    }

    public float val7() {
        return this.val7;
    }

    public ClientboundBmInstallBombPacket val8(final float val8) {
        this.val8 = val8;
        return this;
    }

    public float val8() {
        return this.val8;
    }

    @Override
    public int packetId() {
        return 280;
    }

    @Override
    public void write(final ByteBuf buffer) {
        buffer.writeIntLE(this.val);
        buffer.writeIntLE(this.val2);
        buffer.writeFloatLE(this.val3);
        buffer.writeFloatLE(this.val4);
        buffer.writeFloatLE(this.val5);
        buffer.writeFloatLE(this.val6);
        buffer.writeFloatLE(this.val7);
        buffer.writeFloatLE(this.val8);
    }
}