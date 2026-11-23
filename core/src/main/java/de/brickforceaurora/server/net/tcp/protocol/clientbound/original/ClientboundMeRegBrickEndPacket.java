package de.brickforceaurora.server.net.tcp.protocol.clientbound.original;

import de.brickforceaurora.server.net.tcp.protocol.IClientboundPacket;
import de.brickforceaurora.server.net.tcp.protocol.PacketBuf;

public final class ClientboundMeRegBrickEndPacket implements IClientboundPacket {

    private boolean val;

    public ClientboundMeRegBrickEndPacket val(final boolean val) {
        this.val = val;
        return this;
    }

    public boolean val() {
        return this.val;
    }

    @Override
    public int packetId() {
        return 341;
    }

    @Override
    public void write(final PacketBuf buf) {
        buf.writeBoolean(this.val);
    }
}