package de.brickforceaurora.gameserver.net.protocol.clientbound;

import de.brickforceaurora.gameserver.net.protocol.IClientboundPacket;
import io.netty.buffer.ByteBuf;

public final class ClientboundHashCheckErrorPacket implements IClientboundPacket {

    @Override
    public int packetId() {
        return 396;
    }

    @Override
    public void write(final ByteBuf buffer) {}
}