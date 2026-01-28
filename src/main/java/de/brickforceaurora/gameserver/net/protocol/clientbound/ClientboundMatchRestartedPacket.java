package de.brickforceaurora.gameserver.net.protocol.clientbound;

import de.brickforceaurora.gameserver.net.protocol.IClientboundPacket;
import io.netty.buffer.ByteBuf;

public final class ClientboundMatchRestartedPacket implements IClientboundPacket {

    @Override
    public int packetId() {
        return 267;
    }

    @Override
    public void write(final ByteBuf buffer) {}
}