package de.brickforceaurora.server.net.protocol.clientbound.aurora;

import java.io.IOException;

import de.brickforceaurora.server.net.protocol.IClientboundPacket;
import de.brickforceaurora.server.net.protocol.PacketBuf;
import de.brickforceaurora.server.net.protocol.ProtocolExtension;

public class ClientboundAuroraLoggedInPacket implements IClientboundPacket {

    @Override
    public int packetId() {
        return ProtocolExtension.CLIENTBOUND_AURORA_LOGGED_IN;
    }

    @Override
    public void write(PacketBuf buffer) throws IOException {}

}
