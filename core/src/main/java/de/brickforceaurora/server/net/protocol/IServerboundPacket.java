package de.brickforceaurora.server.net.protocol;

import java.io.IOException;

public interface IServerboundPacket extends IPacket {

    void read(PacketBuf buffer) throws IOException;

}
