package de.brickforceaurora.server.net.tcp.protocol.serverbound.original;

import de.brickforceaurora.server.net.tcp.protocol.IServerboundPacket;
import de.brickforceaurora.server.net.tcp.protocol.PacketBuf;

public final class ServerboundAddBanPacket implements IServerboundPacket {

    private int banWannabe;

    public ServerboundAddBanPacket banWannabe(final int banWannabe) {
        this.banWannabe = banWannabe;
        return this;
    }

    public int banWannabe() {
        return this.banWannabe;
    }

    @Override
    public int packetId() {
        return 105;
    }

    @Override
    public void read(final PacketBuf buf) {
        this.banWannabe = buf.readInt();
    }
}