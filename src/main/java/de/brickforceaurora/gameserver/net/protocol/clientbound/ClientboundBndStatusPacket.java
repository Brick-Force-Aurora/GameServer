package de.brickforceaurora.gameserver.net.protocol.clientbound;

import de.brickforceaurora.gameserver.net.protocol.IClientboundPacket;
import io.netty.buffer.ByteBuf;

public final class ClientboundBndStatusPacket implements IClientboundPacket {

    private boolean val;

    public ClientboundBndStatusPacket val(final boolean val) {
        this.val = val;
        return this;
    }

    public boolean val() {
        return this.val;
    }

    @Override
    public int packetId() {
        return 506;
    }

    @Override
    public void write(final ByteBuf buffer) {
        buffer.writeBoolean(this.val);
    }
}