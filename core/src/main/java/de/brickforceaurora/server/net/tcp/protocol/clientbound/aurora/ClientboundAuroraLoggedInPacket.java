package de.brickforceaurora.server.net.tcp.protocol.clientbound.aurora;

import java.io.IOException;

import de.brickforceaurora.server.net.tcp.protocol.IClientboundPacket;
import de.brickforceaurora.server.net.tcp.protocol.PacketBuf;
import de.brickforceaurora.server.net.tcp.protocol.ProtocolExtension;

public class ClientboundAuroraLoggedInPacket implements IClientboundPacket {

    @Override
    public int packetId() {
        return ProtocolExtension.CLIENTBOUND_AURORA_LOGGED_IN;
    }

    @Override
    public void write(final PacketBuf buffer) throws IOException {}

}
