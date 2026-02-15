package de.brickforceaurora.server.net.protocol.clientbound.aurora;

import java.io.IOException;

import de.brickforceaurora.server.net.protocol.IClientboundPacket;
import de.brickforceaurora.server.net.protocol.PacketBuf;
import de.brickforceaurora.server.net.protocol.ProtocolExtension;

public class ClientboundAuroraDisconnectPacket implements IClientboundPacket {

    private String message;

    public final ClientboundAuroraDisconnectPacket message(String message) {
        this.message = message;
        return this;
    }

    public final String message() {
        return this.message;
    }

    @Override
    public int packetId() {
        return ProtocolExtension.CLIENTBOUND_AURORA_DISCONNECT;
    }

    @Override
    public void write(PacketBuf buffer) throws IOException {
        buffer.writeString(message);
    }

}
