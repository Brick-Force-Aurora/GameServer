package de.brickforceaurora.server.net.protocol.serverbound.original;

import de.brickforceaurora.server.net.protocol.IServerboundPacket;
import de.brickforceaurora.server.net.protocol.PacketBuf;

public final class ServerboundSelectClanIntroPacket implements IServerboundPacket {

    private int clan;

    public ServerboundSelectClanIntroPacket clan(final int clan) {
        this.clan = clan;
        return this;
    }

    public int clan() {
        return this.clan;
    }

    @Override
    public int packetId() {
        return 203;
    }

    @Override
    public void read(final PacketBuf buf) {
        this.clan = buf.readInt();
    }
}