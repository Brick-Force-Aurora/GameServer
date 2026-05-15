package de.brickforceaurora.server.net.protocol.clientbound.aurora;

import java.io.IOException;

import de.brickforceaurora.server.net.login.ILoginDetails;
import de.brickforceaurora.server.net.protocol.IClientboundPacket;
import de.brickforceaurora.server.net.protocol.PacketBuf;
import de.brickforceaurora.server.net.protocol.ProtocolExtension;

public class ClientboundAuroraLoginDetailsPacket implements IClientboundPacket {

    private ILoginDetails details;

    public final ClientboundAuroraLoginDetailsPacket details(ILoginDetails details) {
        this.details = details;
        return this;
    }

    public final ILoginDetails details() {
        return this.details;
    }

    @Override
    public int packetId() {
        return ProtocolExtension.CLIENTBOUND_AURORA_LOGIN_DETAILS;
    }

    @Override
    public void write(final PacketBuf buffer) throws IOException {
        buffer.writeInt(details.type().mask());
        details.write(buffer);
    }

    @Override
    public boolean encrypted() {
        return true;
    }

}
