package de.brickforceaurora.server.net.tcp.protocol.clientbound.original;

import de.brickforceaurora.server.net.tcp.protocol.IClientboundPacket;
import de.brickforceaurora.server.net.tcp.protocol.PacketBuf;

public final class ClientboundEscapeStatusPacket implements IClientboundPacket {

    @Override
    public int packetId() {
        return 522;
    }

    @Override
    public void write(final PacketBuf buf) {}
}