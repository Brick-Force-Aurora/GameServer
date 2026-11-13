package de.brickforceaurora.server.net.rendezvous.protocol;

public final class P2PProtocolConstant {

    public static final int HEADER_SIZE = 8;
    public static final int MAX_DATAGRAM_SIZE = 1400;

    public static final int NO_META = 0xFFFF;
    public static final int BROADCAST = 0xFF;

    private P2PProtocolConstant() {
        throw new UnsupportedOperationException();
    }

}
