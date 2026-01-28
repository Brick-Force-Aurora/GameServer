package de.brickforceaurora.gameserver.net.protocol.clientbound;

import de.brickforceaurora.gameserver.net.protocol.IClientboundPacket;
import io.netty.buffer.ByteBuf;

public final class ClientboundItemPimpPacket implements IClientboundPacket {

    private long val;
    private int val2;
    private int val3;

    public ClientboundItemPimpPacket val(final long val) {
        this.val = val;
        return this;
    }

    public long val() {
        return this.val;
    }

    public ClientboundItemPimpPacket val2(final int val2) {
        this.val2 = val2;
        return this;
    }

    public int val2() {
        return this.val2;
    }

    public ClientboundItemPimpPacket val3(final int val3) {
        this.val3 = val3;
        return this;
    }

    public int val3() {
        return this.val3;
    }

    @Override
    public int packetId() {
        return 355;
    }

    @Override
    public void write(final ByteBuf buffer) {
        buffer.writeLongLE(this.val);
        buffer.writeIntLE(this.val2);
        buffer.writeIntLE(this.val3);
    }
}