package de.brickforceaurora.gameserver.net.protocol.clientbound;

import de.brickforceaurora.gameserver.net.protocol.IClientboundPacket;
import io.netty.buffer.ByteBuf;

public final class ClientboundCtfDropFlagPacket implements IClientboundPacket {

    private int Unnamed0;
    private int Unnamed1;
    private float val3;
    private float val4;
    private float val5;

    public ClientboundCtfDropFlagPacket Unnamed0(final int Unnamed0) {
        this.Unnamed0 = Unnamed0;
        return this;
    }

    public int Unnamed0() {
        return this.Unnamed0;
    }

    public ClientboundCtfDropFlagPacket Unnamed1(final int Unnamed1) {
        this.Unnamed1 = Unnamed1;
        return this;
    }

    public int Unnamed1() {
        return this.Unnamed1;
    }

    public ClientboundCtfDropFlagPacket val3(final float val3) {
        this.val3 = val3;
        return this;
    }

    public float val3() {
        return this.val3;
    }

    public ClientboundCtfDropFlagPacket val4(final float val4) {
        this.val4 = val4;
        return this;
    }

    public float val4() {
        return this.val4;
    }

    public ClientboundCtfDropFlagPacket val5(final float val5) {
        this.val5 = val5;
        return this;
    }

    public float val5() {
        return this.val5;
    }

    @Override
    public int packetId() {
        return 290;
    }

    @Override
    public void write(final ByteBuf buffer) {
        buffer.writeIntLE(this.Unnamed0);
        buffer.writeIntLE(this.Unnamed1);
        buffer.writeFloatLE(this.val3);
        buffer.writeFloatLE(this.val4);
        buffer.writeFloatLE(this.val5);
    }
}