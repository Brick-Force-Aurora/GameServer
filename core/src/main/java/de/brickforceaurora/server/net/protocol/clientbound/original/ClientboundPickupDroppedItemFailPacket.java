package de.brickforceaurora.server.net.protocol.clientbound.original;

import de.brickforceaurora.server.net.protocol.IClientboundPacket;
import de.brickforceaurora.server.net.protocol.PacketBuf;

public final class ClientboundPickupDroppedItemFailPacket implements IClientboundPacket {

    @Override
    public int packetId() {
        return 533;
    }

    @Override
    public void write(final PacketBuf buf) {
        buf.writeInt(-1); //unused
    }
}
