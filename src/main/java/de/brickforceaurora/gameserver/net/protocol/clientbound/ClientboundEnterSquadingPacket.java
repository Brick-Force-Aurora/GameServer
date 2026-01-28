package de.brickforceaurora.gameserver.net.protocol.clientbound;

import de.brickforceaurora.gameserver.net.protocol.IClientboundPacket;
import io.netty.buffer.ByteBuf;

public final class ClientboundEnterSquadingPacket implements IClientboundPacket {

    private int val;

    public ClientboundEnterSquadingPacket val(final int val) {
        this.val = val;
        return this;
    }

    public int val() {
        return this.val;
    }

    @Override
    public int packetId() {
        return 254;
    }

    @Override
    public void write(final ByteBuf buffer) {
        buffer.writeIntLE(this.val);
    }
}