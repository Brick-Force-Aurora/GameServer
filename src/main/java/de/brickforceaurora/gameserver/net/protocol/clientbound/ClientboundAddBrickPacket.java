package de.brickforceaurora.gameserver.net.protocol.clientbound;

import de.brickforceaurora.gameserver.net.protocol.IClientboundPacket;
import io.netty.buffer.ByteBuf;

public final class ClientboundAddBrickPacket implements IClientboundPacket {

    private int val;
    private int val2;
    private int val3;
    private int val4;
    private int val5;
    private int val6;
    private int val7;

    public ClientboundAddBrickPacket val(final int val) {
        this.val = val;
        return this;
    }

    public int val() {
        return this.val;
    }

    public ClientboundAddBrickPacket val2(final int val2) {
        this.val2 = val2;
        return this;
    }

    public int val2() {
        return this.val2;
    }

    public ClientboundAddBrickPacket val3(final int val3) {
        if (val3 > 255L || val3 < 0L) {
            throw new IllegalArgumentException("Value " + val3 + " is out of bounds of allowed number range of 0 - 255");
        }
        this.val3 = val3;
        return this;
    }

    public int val3() {
        return this.val3;
    }

    public ClientboundAddBrickPacket val4(final int val4) {
        if (val4 > 255L || val4 < 0L) {
            throw new IllegalArgumentException("Value " + val4 + " is out of bounds of allowed number range of 0 - 255");
        }
        this.val4 = val4;
        return this;
    }

    public int val4() {
        return this.val4;
    }

    public ClientboundAddBrickPacket val5(final int val5) {
        if (val5 > 255L || val5 < 0L) {
            throw new IllegalArgumentException("Value " + val5 + " is out of bounds of allowed number range of 0 - 255");
        }
        this.val5 = val5;
        return this;
    }

    public int val5() {
        return this.val5;
    }

    public ClientboundAddBrickPacket val6(final int val6) {
        if (val6 > 255L || val6 < 0L) {
            throw new IllegalArgumentException("Value " + val6 + " is out of bounds of allowed number range of 0 - 255");
        }
        this.val6 = val6;
        return this;
    }

    public int val6() {
        return this.val6;
    }

    public ClientboundAddBrickPacket val7(final int val7) {
        if (val7 > 255L || val7 < 0L) {
            throw new IllegalArgumentException("Value " + val7 + " is out of bounds of allowed number range of 0 - 255");
        }
        this.val7 = val7;
        return this;
    }

    public int val7() {
        return this.val7;
    }

    @Override
    public int packetId() {
        return 14;
    }

    @Override
    public void write(final ByteBuf buffer) {
        buffer.writeIntLE(this.val);
        buffer.writeIntLE(this.val2);
        buffer.writeByte(this.val3 & 0xFF);
        buffer.writeByte(this.val4 & 0xFF);
        buffer.writeByte(this.val5 & 0xFF);
        buffer.writeByte(this.val6 & 0xFF);
        buffer.writeByte(this.val7 & 0xFF);
    }
}