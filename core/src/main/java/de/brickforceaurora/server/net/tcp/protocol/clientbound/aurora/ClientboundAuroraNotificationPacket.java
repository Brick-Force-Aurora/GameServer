package de.brickforceaurora.server.net.tcp.protocol.clientbound.aurora;

import java.io.IOException;

import de.brickforceaurora.server.net.tcp.protocol.IClientboundPacket;
import de.brickforceaurora.server.net.tcp.protocol.PacketBuf;
import de.brickforceaurora.server.net.tcp.protocol.ProtocolExtension;

public class ClientboundAuroraNotificationPacket implements IClientboundPacket {

    private String message;

    public final ClientboundAuroraNotificationPacket message(final String message) {
        this.message = message;
        return this;
    }

    public final String message() {
        return this.message;
    }

    @Override
    public int packetId() {
        return ProtocolExtension.CLIENTBOUND_AURORA_NOTIFICATION;
    }

    @Override
    public void write(final PacketBuf buffer) throws IOException {
        buffer.writeString(message);
    }

}
