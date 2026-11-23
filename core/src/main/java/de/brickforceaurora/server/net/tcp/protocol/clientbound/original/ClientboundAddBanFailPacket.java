package de.brickforceaurora.server.net.tcp.protocol.clientbound.original;

import de.brickforceaurora.server.net.tcp.protocol.IClientboundPacket;
import de.brickforceaurora.server.net.tcp.protocol.PacketBuf;

public final class ClientboundAddBanFailPacket implements IClientboundPacket {

    @Override
    public int packetId() {
        return 115;
    }

    @Override
    public void write(final PacketBuf buf) {
        buf.writeInt(0); //unused
    }
}