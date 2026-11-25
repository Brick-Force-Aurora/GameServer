package de.brickforceaurora.server.net.tcp.protocol;

import de.brickforceaurora.server.net.tcp.protocol.clientbound.aurora.ClientboundAuroraHeartbeatPacket;
import de.brickforceaurora.server.net.tcp.protocol.clientbound.aurora.ClientboundAuroraLoggedInPacket;

public final class ProtocolExtension {

    // Core functionality in 1000-1099
    public static final int SERVERBOUND_AURORA_HEARTBEAT = 1000;
    public static final int CLIENTBOUND_AURORA_HEARTBEAT = 1001;
    public static final int CLIENTBOUND_AURORA_NOTIFICATION = 1002;
    public static final int CLIENTBOUND_AURORA_DISCONNECT = 1003;

    // Handshake/Login should be in 1100-1199
    public static final int SERVERBOUND_AURORA_HANDSHAKE_INITIALIZE = 1004;
    public static final int CLIENTBOUND_AURORA_HANDSHAKE_CHALLENGE = 1005;
    public static final int SERVERBOUND_AURORA_HANDSHAKE_COMPLETE = 1006;
    public static final int CLIENTBOUND_AURORA_REQUEST_LOGIN = 1007;
    public static final int SERVERBOUND_AURORA_LOGIN = 1008;
    public static final int CLIENTBOUND_AURORA_LOGIN_DETAILS = 1009;
    public static final int CLIENTBOUND_AURORA_LOGGED_IN = 1010;

    // Anything else should be 1200-1999

    // Duplex packets should be in 2000-2999
    public static final int DUPLEX_BUFFER_BEGIN = 2000;
    public static final int DUPLEX_BUFFER_CHUNK = 2001;
    public static final int DUPLEX_BUFFER_END = 2002;

    public static final ClientboundAuroraHeartbeatPacket PACKET_HEARTBEAT = new ClientboundAuroraHeartbeatPacket();
    public static final ClientboundAuroraLoggedInPacket PACKET_LOGGED_IN = new ClientboundAuroraLoggedInPacket();

    private ProtocolExtension() {
        throw new UnsupportedOperationException();
    }

}
