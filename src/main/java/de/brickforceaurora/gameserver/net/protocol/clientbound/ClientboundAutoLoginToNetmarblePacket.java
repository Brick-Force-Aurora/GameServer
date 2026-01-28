package de.brickforceaurora.gameserver.net.protocol.clientbound;

import de.brickforceaurora.gameserver.net.protocol.IClientboundPacket;
import io.netty.buffer.ByteBuf;

public final class ClientboundAutoLoginToNetmarblePacket implements IClientboundPacket {

    private int val;
    private int val2;
    private boolean val3;
    private int val4;
    private int val5;
    private int val6;
    private int val7;

    public ClientboundAutoLoginToNetmarblePacket val(final int val) {
        this.val = val;
        return this;
    }

    public int val() {
        return this.val;
    }

    public ClientboundAutoLoginToNetmarblePacket val2(final int val2) {
        this.val2 = val2;
        return this;
    }

    public int val2() {
        return this.val2;
    }

    public ClientboundAutoLoginToNetmarblePacket val3(final boolean val3) {
        this.val3 = val3;
        return this;
    }

    public boolean val3() {
        return this.val3;
    }

    public ClientboundAutoLoginToNetmarblePacket val4(final int val4) {
        this.val4 = val4;
        return this;
    }

    public int val4() {
        return this.val4;
    }

    public ClientboundAutoLoginToNetmarblePacket val5(final int val5) {
        this.val5 = val5;
        return this;
    }

    public int val5() {
        return this.val5;
    }

    public ClientboundAutoLoginToNetmarblePacket val6(final int val6) {
        if (val6 > 255L || val6 < 0L) {
            throw new IllegalArgumentException("Value " + val6 + " is out of bounds of allowed number range of 0 - 255");
        }
        this.val6 = val6;
        return this;
    }

    public int val6() {
        return this.val6;
    }

    public ClientboundAutoLoginToNetmarblePacket val7(final int val7) {
        this.val7 = val7;
        return this;
    }

    public int val7() {
        return this.val7;
    }

    @Override
    public int packetId() {
        return 459;
    }

    @Override
    public void write(final ByteBuf buffer) {
        buffer.writeIntLE(this.val);
        buffer.writeIntLE(this.val2);
        buffer.writeBoolean(this.val3);
        buffer.writeIntLE(this.val4);
        buffer.writeIntLE(this.val5);
        buffer.writeByte(this.val6 & 0xFF);
        buffer.writeIntLE(this.val7);
    }
}