package de.brickforceaurora.gameserver.net.protocol.clientbound;

import de.brickforceaurora.gameserver.net.protocol.IClientboundPacket;
import io.netty.buffer.ByteBuf;

public final class ClientboundClanCancelApplicationPacket implements IClientboundPacket {

    @Override
    public int packetId() {
        return 558;
    }

    @Override
    public void write(final ByteBuf buffer) {}
}