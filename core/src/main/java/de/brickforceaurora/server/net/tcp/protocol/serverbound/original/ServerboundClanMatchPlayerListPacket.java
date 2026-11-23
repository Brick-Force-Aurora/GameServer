package de.brickforceaurora.server.net.tcp.protocol.serverbound.original;

import de.brickforceaurora.server.net.tcp.protocol.IServerboundPacket;
import de.brickforceaurora.server.net.tcp.protocol.PacketBuf;

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
    public void read(final PacketBuf buf) {
        this.clanMatch = buf.readLong();
    }
}