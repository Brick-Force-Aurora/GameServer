package de.brickforceaurora.gameserver.net.protocol.clientbound;

import de.brickforceaurora.gameserver.net.protocol.IClientboundPacket;
import io.netty.buffer.ByteBuf;

public final class ClientboundShooterToolPacket implements IClientboundPacket {

    private byte val;
    private long val2;

    public ClientboundShooterToolPacket val(final byte val) {
        this.val = val;
        return this;
    }

    public byte val() {
        return this.val;
    }

    public ClientboundShooterToolPacket val2(final long val2) {
        this.val2 = val2;
        return this;
    }

    public long val2() {
        return this.val2;
    }

    @Override
    public int packetId() {
        return 332;
    }

    @Override
    public void write(final ByteBuf buffer) {
        buffer.writeByte(this.val);
        buffer.writeLongLE(this.val2);
    }
}