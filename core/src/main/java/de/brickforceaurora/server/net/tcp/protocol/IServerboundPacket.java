package de.brickforceaurora.server.net.tcp.protocol;

import java.io.IOException;

public interface IServerboundPacket extends IPacket {

    void read(PacketBuf buffer) throws IOException;

    default boolean requiresLogIn() {
        return true;
    }

}
