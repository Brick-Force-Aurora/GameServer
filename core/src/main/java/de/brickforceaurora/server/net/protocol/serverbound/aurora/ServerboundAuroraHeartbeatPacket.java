package de.brickforceaurora.server.net.protocol.serverbound.aurora;

import java.io.IOException;

import de.brickforceaurora.server.net.protocol.IServerboundPacket;
import de.brickforceaurora.server.net.protocol.PacketBuf;
import de.brickforceaurora.server.net.protocol.ProtocolExtension;

public class ServerboundAuroraHeartbeatPacket implements IServerboundPacket {

    @Override
    public int packetId() {
        return ProtocolExtension.SERVERBOUND_AURORA_HEARTBEAT;
    }

    @Override
    public void read(PacketBuf buffer) throws IOException {}
    
    @Override
    public boolean requiresLogIn() {
        return false;
    }

}
