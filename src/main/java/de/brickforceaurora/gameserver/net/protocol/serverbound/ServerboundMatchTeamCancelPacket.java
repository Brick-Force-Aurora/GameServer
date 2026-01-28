package de.brickforceaurora.gameserver.net.protocol.serverbound;

import de.brickforceaurora.gameserver.net.protocol.IServerboundPacket;
import io.netty.buffer.ByteBuf;

public final class ServerboundMatchTeamCancelPacket implements IServerboundPacket {

    @Override
    public int packetId() {
        return 272;
    }

    @Override
    public void read(final ByteBuf buffer) {}
}