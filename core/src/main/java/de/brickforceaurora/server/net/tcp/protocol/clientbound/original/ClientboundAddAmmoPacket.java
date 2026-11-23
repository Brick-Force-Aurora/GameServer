package de.brickforceaurora.server.net.tcp.protocol.clientbound.original;

import de.brickforceaurora.server.net.tcp.protocol.IClientboundPacket;
import de.brickforceaurora.server.net.tcp.protocol.PacketBuf;

public final class ClientboundAddAmmoPacket implements IClientboundPacket {

    @Override
    public int packetId() {
        return 550;
    }

    @Override
    public void write(final PacketBuf buf) {}
}