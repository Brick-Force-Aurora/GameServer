package de.brickforceaurora.gameserver.net.protocol.clientbound;

import de.brickforceaurora.gameserver.net.protocol.IClientboundPacket;
import io.netty.buffer.ByteBuf;

public final class ClientboundShooterToolListPacket implements IClientboundPacket {

    private int val;
    private byte val2;
    private long val3;

    public ClientboundShooterToolListPacket val(final int val) {
        this.val = val;
        return this;
    }

    public int val() {
        return this.val;
    }

    public ClientboundShooterToolListPacket val2(final byte val2) {
        this.val2 = val2;
        return this;
    }

    public byte val2() {
        return this.val2;
    }

    public ClientboundShooterToolListPacket val3(final long val3) {
        this.val3 = val3;
        return this;
    }

    public long val3() {
        return this.val3;
    }

    @Override
    public int packetId() {
        return 462;
    }

    @Override
    public void write(final ByteBuf buffer) {
        buffer.writeIntLE(this.val);
        buffer.writeByte(this.val2);
        buffer.writeLongLE(this.val3);
    }
}