package de.brickforceaurora.server.net.protocol;

import de.brickforceaurora.server.net.protocol.clientbound.aurora.*;

public final class ProtocolExtension {

    public static final int SERVERBOUND_AURORA_HEARTBEAT = 1000;
    public static final int CLIENTBOUND_AURORA_HEARTBEAT = 1001;
    public static final int SERVERBOUND_AURORA_DISCONNECT = 1002;
    public static final int CLIENTBOUND_AURORA_DISCONNECT = 1003;
    public static final int SERVERBOUND_AURORA_HANDSHAKE_INITIALIZE = 1004;
    public static final int CLIENTBOUND_AURORA_HANDSHAKE_CHALLENGE = 1005;
    public static final int SERVERBOUND_AURORA_HANDSHAKE_COMPLETE = 1006;
    public static final int CLIENTBOUND_AURORA_REQUEST_LOGIN = 1007;
    public static final int CLIENTBOUND_AURORA_LOGGED_IN = 1008;
    public static final int SERVERBOUND_AURORA_LOGIN = 1009;

    public static final ClientboundAuroraHeartbeatPacket PACKET_HEARTBEAT = new ClientboundAuroraHeartbeatPacket();
    public static final ClientboundAuroraLoggedInPacket PACKET_LOGGED_IN = new ClientboundAuroraLoggedInPacket();

    private ProtocolExtension() {
        throw new UnsupportedOperationException();
    }

}
