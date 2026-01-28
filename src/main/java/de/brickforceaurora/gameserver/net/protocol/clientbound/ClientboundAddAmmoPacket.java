package de.brickforceaurora.gameserver.net.protocol.clientbound;

import de.brickforceaurora.gameserver.net.protocol.IClientboundPacket;
import io.netty.buffer.ByteBuf;

public final class ClientboundAddAmmoPacket implements IClientboundPacket {

    @Override
    public int packetId() {
        return 550;
    }

    @Override
    public void write(final ByteBuf buffer) {}
}