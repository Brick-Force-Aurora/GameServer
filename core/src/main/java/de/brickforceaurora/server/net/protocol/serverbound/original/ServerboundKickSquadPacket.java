package de.brickforceaurora.server.net.protocol.serverbound.original;

import de.brickforceaurora.server.net.protocol.IServerboundPacket;
import de.brickforceaurora.server.net.protocol.PacketBuf;

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
    public void read(final PacketBuf buf) {
        this.kicked = buf.readInt();
    }
}