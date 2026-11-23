package de.brickforceaurora.server.net.tcp.protocol.serverbound.original;

import de.brickforceaurora.server.net.tcp.protocol.IServerboundPacket;
import de.brickforceaurora.server.net.tcp.protocol.PacketBuf;

public final class ServerboundDestroyClanPacket implements IServerboundPacket {

    private int clan;

    public ServerboundDestroyClanPacket clan(final int clan) {
        this.clan = clan;
        return this;
    }

    public int clan() {
        return this.clan;
    }

    @Override
    public int packetId() {
        return 191;
    }

    @Override
    public void read(final PacketBuf buf) {
        this.clan = buf.readInt();
    }
}