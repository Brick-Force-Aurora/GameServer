package de.brickforceaurora.server.net.tcp.protocol.serverbound.original;

import de.brickforceaurora.server.net.tcp.protocol.IServerboundPacket;
import de.brickforceaurora.server.net.tcp.protocol.PacketBuf;

public final class ServerboundKickoutVotePacket implements IServerboundPacket {

    private boolean yes;

    public ServerboundKickoutVotePacket yes(final boolean yes) {
        this.yes = yes;
        return this;
    }

    public boolean yes() {
        return this.yes;
    }

    @Override
    public int packetId() {
        return 496;
    }

    @Override
    public void read(final PacketBuf buf) {
        this.yes = buf.readBoolean();
    }
}