package de.brickforceaurora.server.net.protocol;

public interface IPacket {

    int packetId();
    
    default boolean encrypted() {
        return false;
    }

    default String packetName() {
        return getClass().getSimpleName();
    }

}
