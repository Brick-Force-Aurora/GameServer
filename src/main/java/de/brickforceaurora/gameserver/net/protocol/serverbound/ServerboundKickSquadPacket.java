package de.brickforceaurora.gameserver.net.protocol.serverbound;

import de.brickforceaurora.gameserver.net.protocol.IServerboundPacket;
import io.netty.buffer.ByteBuf;

public final class ServerboundKickSquadPacket implements IServerboundPacket {

    private int kicked;

    public ServerboundKickSquadPacket kicked(final int kicked) {
        this.kicked = kicked;
        return this;
    }

    public int kicked() {
        return this.kicked;
    }

    @Override
    public int packetId() {
        return 257;
    }

    @Override
    public void read(final ByteBuf buffer) {
        this.kicked = buffer.readIntLE();
    }
}