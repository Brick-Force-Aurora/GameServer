package de.brickforceaurora.server.net.protocol;

public interface IPacket {

    int packetId();

    default String packetName() {
        return getClass().getSimpleName();
    }

}
