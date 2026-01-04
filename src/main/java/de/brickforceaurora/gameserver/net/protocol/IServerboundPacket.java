package de.brickforceaurora.gameserver.net.protocol;

import io.netty.buffer.ByteBuf;

public interface IServerboundPacket extends IPacket {
    
    void read(ByteBuf buffer);

}
