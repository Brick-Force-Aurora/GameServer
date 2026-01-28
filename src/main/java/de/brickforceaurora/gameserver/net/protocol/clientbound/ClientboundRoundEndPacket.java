package de.brickforceaurora.gameserver.net.protocol.clientbound;

import de.brickforceaurora.gameserver.net.protocol.IClientboundPacket;
import io.netty.buffer.ByteBuf;

public final class ClientboundRoundEndPacket implements IClientboundPacket {

    private int val;
    private byte val2;
    private byte val3;
    private byte val4;

    public ClientboundRoundEndPacket val(final int val) {
        this.val = val;
        return this;
    }

    public int val() {
        return this.val;
    }

    public ClientboundRoundEndPacket val2(final byte val2) {
        this.val2 = val2;
        return this;
    }

    public byte val2() {
        return this.val2;
    }

    public ClientboundRoundEndPacket val3(final byte val3) {
        this.val3 = val3;
        return this;
    }

    public byte val3() {
        return this.val3;
    }

    public ClientboundRoundEndPacket val4(final byte val4) {
        this.val4 = val4;
        return this;
    }

    public byte val4() {
        return this.val4;
    }

    @Override
    public int packetId() {
        return 205;
    }

    @Override
    public void write(final ByteBuf buffer) {
        buffer.writeIntLE(this.val);
        buffer.writeByte(this.val2);
        buffer.writeByte(this.val3);
        buffer.writeByte(this.val4);
    }
}