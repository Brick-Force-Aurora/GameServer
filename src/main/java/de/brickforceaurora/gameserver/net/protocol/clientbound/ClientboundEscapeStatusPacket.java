package de.brickforceaurora.gameserver.net.protocol.clientbound;

import de.brickforceaurora.gameserver.net.protocol.IClientboundPacket;
import io.netty.buffer.ByteBuf;

public final class ClientboundEscapeStatusPacket implements IClientboundPacket {

    @Override
    public int packetId() {
        return 522;
    }

    @Override
    public void write(final ByteBuf buffer) {}
}