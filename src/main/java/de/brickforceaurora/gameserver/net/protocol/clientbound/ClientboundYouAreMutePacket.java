package de.brickforceaurora.gameserver.net.protocol.clientbound;

import de.brickforceaurora.gameserver.net.protocol.IClientboundPacket;
import io.netty.buffer.ByteBuf;

public final class ClientboundYouAreMutePacket implements IClientboundPacket {

    private long val;

    public ClientboundYouAreMutePacket val(final long val) {
        if (val > 4294967295L || val < 0L) {
            throw new IllegalArgumentException("Value " + val + " is out of bounds of allowed number range of 0 - 4294967295");
        }
        this.val = val;
        return this;
    }

    public long val() {
        return this.val;
    }

    @Override
    public int packetId() {
        return 457;
    }

    @Override
    public void write(final ByteBuf buffer) {
        buffer.writeIntLE((int) this.val);
    }
}