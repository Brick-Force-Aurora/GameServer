package de.brickforceaurora.gameserver.net.protocol;

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

    private ProtocolConstant() {
        throw new UnsupportedOperationException();
    }
    
}
