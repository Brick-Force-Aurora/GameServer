package de.brickforceaurora.gameserver.net.protocol.clientbound;

import de.brickforceaurora.gameserver.net.protocol.IClientboundPacket;
import io.netty.buffer.ByteBuf;

public final class ClientboundTermItemExpiredPacket implements IClientboundPacket {

    private long val;

    public ClientboundTermItemExpiredPacket val(final long val) {
        this.val = val;
        return this;
    }

    public long val() {
        return this.val;
    }

    @Override
    public int packetId() {
        return 124;
    }

    @Override
    public void write(final ByteBuf buffer) {
        buffer.writeLongLE(this.val);
    }
}