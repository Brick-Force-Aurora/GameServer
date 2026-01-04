package de.brickforceaurora.gameserver.net.protocol;

import java.io.IOException;

import io.netty.buffer.ByteBuf;

public interface IServerboundPacket extends IPacket {
    
    void read(ByteBuf buffer) throws IOException;

}
