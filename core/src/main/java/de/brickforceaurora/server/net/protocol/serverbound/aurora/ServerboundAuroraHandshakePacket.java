package de.brickforceaurora.server.net.protocol.serverbound.aurora;

import java.io.IOException;
import java.security.PublicKey;

import de.brickforceaurora.server.net.protocol.IServerboundPacket;
import de.brickforceaurora.server.net.protocol.PacketBuf;
import de.brickforceaurora.server.net.protocol.ProtocolExtension;

public class ServerboundAuroraHandshakePacket implements IServerboundPacket {
    
    private PublicKey clientKey;

    public final ServerboundAuroraHandshakePacket clientKey(PublicKey clientKey) {
        this.clientKey = clientKey;
        return this;
    }

    public final PublicKey clientKey() {
        return this.clientKey;
    }

    @Override
    public int packetId() {
        return ProtocolExtension.SERVERBOUND_AURORA_HANDSHAKE;
    }

    @Override
    public void read(PacketBuf buffer) throws IOException {}

}
