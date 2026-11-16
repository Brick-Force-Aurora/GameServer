package de.brickforceaurora.server.net.protocol.clientbound.original;

import de.brickforceaurora.server.net.protocol.IClientboundPacket;
import de.brickforceaurora.server.net.protocol.PacketBuf;

public final class ClientboundMatchCountdownPacket implements IClientboundPacket {

    private int count;

    public ClientboundMatchCountdownPacket count(final int count) {
        this.count = count;
        return this;
    }

    public int count() {
        return this.count;
    }

    @Override
    public int packetId() {
        return 72;
    }

    @Override
    public void write(final PacketBuf buf) {
        buf.writeInt(this.count);
    }
}