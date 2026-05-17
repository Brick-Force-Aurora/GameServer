package de.brickforceaurora.server.net.protocol.serverbound.aurora;

import java.io.IOException;

import de.brickforceaurora.server.net.protocol.IServerboundPacket;
import de.brickforceaurora.server.net.protocol.PacketBuf;
import de.brickforceaurora.server.net.protocol.ProtocolExtension;

public class ServerboundAuroraHeartbeatPacket implements IServerboundPacket {
    
    private boolean requestsHeartbeat;
    
    public final ServerboundAuroraHeartbeatPacket requestsHeartbeat(boolean requestsHeartbeat) {
        this.requestsHeartbeat = requestsHeartbeat;
        return this;
    }
    
    public final boolean requestsHeartbeat() {
        return requestsHeartbeat;
    }

    @Override
    public int packetId() {
        return ProtocolExtension.SERVERBOUND_AURORA_HEARTBEAT;
    }

    @Override
    public void read(PacketBuf buffer) throws IOException {
        this.requestsHeartbeat = buffer.readBoolean();
    }
    
    @Override
    public boolean requiresLogIn() {
        return false;
    }

}
