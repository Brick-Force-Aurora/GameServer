package de.brickforceaurora.server.net.tcp.protocol.clientbound.aurora;

import java.io.IOException;

import de.brickforceaurora.server.net.tcp.login.LoginType;
import de.brickforceaurora.server.net.tcp.protocol.IClientboundPacket;
import de.brickforceaurora.server.net.tcp.protocol.PacketBuf;
import de.brickforceaurora.server.net.tcp.protocol.ProtocolExtension;
import de.brickforceaurora.server.util.flag.IFlags;

public class ClientboundAuroraRequestLoginPacket implements IClientboundPacket {

    private IFlags<LoginType> supportedTypes;

    public final ClientboundAuroraRequestLoginPacket supportedTypes(final IFlags<LoginType> supportedTypes) {
        this.supportedTypes = supportedTypes;
        return this;
    }

    public final IFlags<LoginType> supportedTypes() {
        return supportedTypes;
    }

    @Override
    public int packetId() {
        return ProtocolExtension.CLIENTBOUND_AURORA_REQUEST_LOGIN;
    }

    @Override
    public void write(final PacketBuf buffer) throws IOException {
        buffer.writeInt(supportedTypes.value());
    }

    @Override
    public boolean encrypted() {
        return true;
    }

}
