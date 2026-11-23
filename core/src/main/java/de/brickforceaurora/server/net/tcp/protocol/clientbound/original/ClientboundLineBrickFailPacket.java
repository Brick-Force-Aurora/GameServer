package de.brickforceaurora.server.net.tcp.protocol.clientbound.original;

import de.brickforceaurora.server.net.tcp.protocol.IClientboundPacket;
import de.brickforceaurora.server.net.tcp.protocol.PacketBuf;

public final class ClientboundLineBrickFailPacket implements IClientboundPacket {

    private int val;

    public ClientboundLineBrickFailPacket val(final int val) {
        this.val = val;
        return this;
    }

    public int val() {
        return this.val;
    }

    @Override
    public int packetId() {
        return 330;
    }

    @Override
    public void write(final PacketBuf buf) {
        buf.writeInt(this.val);
    }
}