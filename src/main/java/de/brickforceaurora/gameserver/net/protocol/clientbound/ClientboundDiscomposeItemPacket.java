package de.brickforceaurora.gameserver.net.protocol.clientbound;

import de.brickforceaurora.gameserver.net.protocol.IClientboundPacket;
import io.netty.buffer.ByteBuf;

public final class ClientboundDiscomposeItemPacket implements IClientboundPacket {

    private int val;
    private long val2;

    public ClientboundDiscomposeItemPacket val(final int val) {
        this.val = val;
        return this;
    }

    public int val() {
        return this.val;
    }

    public ClientboundDiscomposeItemPacket val2(final long val2) {
        this.val2 = val2;
        return this;
    }

    public long val2() {
        return this.val2;
    }

    @Override
    public int packetId() {
        return 317;
    }

    @Override
    public void write(final ByteBuf buffer) {
        buffer.writeIntLE(this.val);
        buffer.writeLongLE(this.val2);
    }
}