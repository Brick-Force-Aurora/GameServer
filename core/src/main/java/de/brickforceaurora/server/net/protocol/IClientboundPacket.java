package de.brickforceaurora.server.net.protocol;

import java.io.IOException;

public interface IClientboundPacket extends IPacket {

    default boolean requiresClientId() {
        return false;
    }

    void write(PacketBuf buffer) throws IOException;

}
