package de.brickforceaurora.server.net.protocol.clientbound.original;

import de.brickforceaurora.server.net.protocol.IClientboundPacket;
import de.brickforceaurora.server.net.protocol.PacketBuf;

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
    public void write(final PacketBuf buf) {
        buf.writeBoolean(this.val);
    }
}