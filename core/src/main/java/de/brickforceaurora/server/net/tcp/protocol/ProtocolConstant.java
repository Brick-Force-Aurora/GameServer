package de.brickforceaurora.server.net.tcp.protocol;

final class ProtocolConstant {

    // TODO: Packets to fix:
    // Clientbound: [ClientboundEnterPacket, ClientboundTcOpenPacket, ClientboundTcChestPacket]
    // Serverbound: [ServerboundAutoLoginToNetmarblePacket, ServerboundXtrapPacket]

    // TODO: Packets to check: 
    // ClientBound: []
    // Serverbound: [ServerboundWeaponHeldRatioPacket, ServerboundKillLogPacket, ServerboundInflictedDamagePacket]

    public static final int HEADER_SIZE = 15;

    public static final byte KEY_MAX_VALUE = (byte) 0xFF;
    public static final byte DEFAULT_RECEIVE_KEY = KEY_MAX_VALUE;
    public static final byte DEFAULT_SEND_KEY = KEY_MAX_VALUE;

    public static final int MIN_SIZE_FOR_CHUNKING = 4096;
    public static final int MAX_CHUNKS = 65535;
    public static final int MAX_CHUNK_LENGTH = 6144;
    public static final int MAX_BUFFER_LIMIT = MAX_CHUNKS * MAX_CHUNK_LENGTH;

    private ProtocolConstant() {
        throw new UnsupportedOperationException();
    }

}
