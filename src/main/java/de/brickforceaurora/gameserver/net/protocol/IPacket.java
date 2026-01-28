package de.brickforceaurora.gameserver.net.protocol;

public interface IPacket {

    int packetId();

    default String packetName() {
        return getClass().getSimpleName();
    }

}
