package de.brickforceaurora.server.net.protocol.clientbound.original;

import de.brickforceaurora.server.net.protocol.IClientboundPacket;
import de.brickforceaurora.server.net.protocol.PacketBuf;

public final class ClientboundShopEndPacket implements IClientboundPacket {

    @Override
    public int packetId() {
        return 322;
    }

    @Override
    public void write(final PacketBuf buf) {}
}