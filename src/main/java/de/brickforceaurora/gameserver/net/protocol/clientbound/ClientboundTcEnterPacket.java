package de.brickforceaurora.gameserver.net.protocol.clientbound;

import de.brickforceaurora.gameserver.net.protocol.IClientboundPacket;
import io.netty.buffer.ByteBuf;

public final class ClientboundTcEnterPacket implements IClientboundPacket {

    private int val;
    private int val2;
    private int val3;
    private int val4;
    private int val5;
    private int val6;

    public ClientboundTcEnterPacket val(final int val) {
        this.val = val;
        return this;
    }

    public int val() {
        return this.val;
    }

    public ClientboundTcEnterPacket val2(final int val2) {
        this.val2 = val2;
        return this;
    }

    public int val2() {
        return this.val2;
    }

    public ClientboundTcEnterPacket val3(final int val3) {
        this.val3 = val3;
        return this;
    }

    public int val3() {
        return this.val3;
    }

    public ClientboundTcEnterPacket val4(final int val4) {
        if (val4 > 255L || val4 < 0L) {
            throw new IllegalArgumentException("Value " + val4 + " is out of bounds of allowed number range of 0 - 255");
        }
        this.val4 = val4;
        return this;
    }

    public int val4() {
        return this.val4;
    }

    public ClientboundTcEnterPacket val5(final int val5) {
        this.val5 = val5;
        return this;
    }

    public int val5() {
        return this.val5;
    }

    public ClientboundTcEnterPacket val6(final int val6) {
        this.val6 = val6;
        return this;
    }

    public int val6() {
        return this.val6;
    }

    @Override
    public int packetId() {
        return 373;
    }

    @Override
    public void write(final ByteBuf buffer) {
        buffer.writeIntLE(this.val);
        buffer.writeIntLE(this.val2);
        buffer.writeIntLE(this.val3);
        buffer.writeByte(this.val4 & 0xFF);
        buffer.writeIntLE(this.val5);
        buffer.writeIntLE(this.val6);
    }
}