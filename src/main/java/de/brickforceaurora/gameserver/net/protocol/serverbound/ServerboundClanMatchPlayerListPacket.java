package de.brickforceaurora.gameserver.net.protocol.serverbound;

import de.brickforceaurora.gameserver.net.protocol.IServerboundPacket;
import io.netty.buffer.ByteBuf;

public final class ServerboundClanMatchPlayerListPacket implements IServerboundPacket {

    private long clanMatch;

    public ServerboundClanMatchPlayerListPacket clanMatch(final long clanMatch) {
        this.clanMatch = clanMatch;
        return this;
    }

    public long clanMatch() {
        return this.clanMatch;
    }

    @Override
    public int packetId() {
        return 270;
    }

    @Override
    public void read(final ByteBuf buffer) {
        this.clanMatch = buffer.readLongLE();
    }
}