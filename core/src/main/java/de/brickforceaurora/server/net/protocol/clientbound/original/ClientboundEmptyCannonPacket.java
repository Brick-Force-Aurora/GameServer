package de.brickforceaurora.server.net.protocol.clientbound.original;

import de.brickforceaurora.server.net.protocol.IClientboundPacket;
import de.brickforceaurora.server.net.protocol.PacketBuf;

public final class ClientboundEmptyCannonPacket implements IClientboundPacket {

    private int cannon;

    public ClientboundEmptyCannonPacket cannon(final int cannon) {
        this.cannon = cannon;
        return this;
    }

    public int cannon() {
        return this.cannon;
    }

    @Override
    public int packetId() {
        return 161;
    }

    @Override
    public void write(final PacketBuf buf) {
        buf.writeInt(this.cannon);
    }
}
