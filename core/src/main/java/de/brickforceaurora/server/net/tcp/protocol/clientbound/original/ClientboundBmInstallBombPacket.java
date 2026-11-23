package de.brickforceaurora.server.net.tcp.protocol.clientbound.original;

import de.brickforceaurora.server.net.tcp.protocol.IClientboundPacket;
import de.brickforceaurora.server.net.tcp.protocol.PacketBuf;

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
    public void write(final PacketBuf buf) {
        buf.writeInt(this.val);
        buf.writeInt(this.val2);
        buf.writeFloat(this.val3);
        buf.writeFloat(this.val4);
        buf.writeFloat(this.val5);
        buf.writeFloat(this.val6);
        buf.writeFloat(this.val7);
        buf.writeFloat(this.val8);
    }
}