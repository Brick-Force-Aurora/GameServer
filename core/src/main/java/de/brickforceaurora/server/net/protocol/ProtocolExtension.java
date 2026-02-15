package de.brickforceaurora.server.net.protocol;

import de.brickforceaurora.server.net.protocol.clientbound.aurora.*;

public final class ProtocolExtension {

    public static final int CLIENTBOUND_AURORA_CONNECTED = 1000;
    public static final int CLIENTBOUND_AURORA_HEARTBEAT = 1001;
    public static final int SERVERBOUND_AURORA_DISCONNECT = 1002;
    public static final int CLIENTBOUND_AURORA_DISCONNECT = 1003;
    public static final int SERVERBOUND_AURORA_HANDSHAKE = 1004;
    public static final int CLIENTBOUND_AURORA_HANDSHAKE = 1005;
    public static final int SERVERBOUND_AURORA_HANDSHAKE_CHALLENGE = 1006;
    public static final int CLIENTBOUND_AURORA_HANDSHAKE_CHALLENGE = 1007;
    public static final int CLIENTBOUND_AURORA_LOGIN = 1008;
    public static final int SERVERBOUND_AURORA_LOGIN = 1009;

    public static final ClientboundAuroraConnectedPacket PACKET_CONNECTED = new ClientboundAuroraConnectedPacket();
    public static final ClientboundAuroraHeartbeatPacket PACKET_HEARTBEAT = new ClientboundAuroraHeartbeatPacket();
    public static final ClientboundAuroraHandshakeChallengePacket PACKET_HANDSHAKE_CHALLENGE = new ClientboundAuroraHandshakeChallengePacket();
    public static final ClientboundAuroraLoginPacket PACKET_LOGIN = new ClientboundAuroraLoginPacket();

    private ProtocolExtension() {
        throw new UnsupportedOperationException();
    }

}
