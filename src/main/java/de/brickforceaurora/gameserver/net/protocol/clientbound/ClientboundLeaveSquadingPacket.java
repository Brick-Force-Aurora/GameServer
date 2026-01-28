package de.brickforceaurora.gameserver.net.protocol.clientbound;

import de.brickforceaurora.gameserver.net.protocol.IClientboundPacket;
import io.netty.buffer.ByteBuf;

public final class ClientboundLeaveSquadingPacket implements IClientboundPacket {

    @Override
    public int packetId() {
        return 256;
    }

    @Override
    public void write(final ByteBuf buffer) {}
}