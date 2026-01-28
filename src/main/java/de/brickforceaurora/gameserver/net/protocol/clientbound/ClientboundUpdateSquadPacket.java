package de.brickforceaurora.gameserver.net.protocol.clientbound;

import de.brickforceaurora.gameserver.net.protocol.IClientboundPacket;
import io.netty.buffer.ByteBuf;

public final class ClientboundUpdateSquadPacket implements IClientboundPacket {

    private int val;
    private int val2;
    private int val3;
    private int val4;
    private int val5;
    private int val6;
    private int val7;
    private int val8;

    public ClientboundUpdateSquadPacket val(final int val) {
        this.val = val;
        return this;
    }

    public int val() {
        return this.val;
    }

    public ClientboundUpdateSquadPacket val2(final int val2) {
        this.val2 = val2;
        return this;
    }

    public int val2() {
        return this.val2;
    }

    public ClientboundUpdateSquadPacket val3(final int val3) {
        this.val3 = val3;
        return this;
    }

    public int val3() {
        return this.val3;
    }

    public ClientboundUpdateSquadPacket val4(final int val4) {
        this.val4 = val4;
        return this;
    }

    public int val4() {
        return this.val4;
    }

    public ClientboundUpdateSquadPacket val5(final int val5) {
        this.val5 = val5;
        return this;
    }

    public int val5() {
        return this.val5;
    }

    public ClientboundUpdateSquadPacket val6(final int val6) {
        this.val6 = val6;
        return this;
    }

    public int val6() {
        return this.val6;
    }

    public ClientboundUpdateSquadPacket val7(final int val7) {
        this.val7 = val7;
        return this;
    }

    public int val7() {
        return this.val7;
    }

    public ClientboundUpdateSquadPacket val8(final int val8) {
        this.val8 = val8;
        return this;
    }

    public int val8() {
        return this.val8;
    }

    @Override
    public int packetId() {
        return 250;
    }

    @Override
    public void write(final ByteBuf buffer) {
        buffer.writeIntLE(this.val);
        buffer.writeIntLE(this.val2);
        buffer.writeIntLE(this.val3);
        buffer.writeIntLE(this.val4);
        buffer.writeIntLE(this.val5);
        buffer.writeIntLE(this.val6);
        buffer.writeIntLE(this.val7);
        buffer.writeIntLE(this.val8);
    }
}