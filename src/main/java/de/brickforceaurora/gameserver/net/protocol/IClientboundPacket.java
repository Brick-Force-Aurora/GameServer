package de.brickforceaurora.gameserver.net.protocol;

import java.io.IOException;

import io.netty.buffer.ByteBuf;

public interface IClientboundPacket extends IPacket {

    default boolean requiresClientId() {
        return false;
    }

    void write(ByteBuf buffer) throws IOException;

}
