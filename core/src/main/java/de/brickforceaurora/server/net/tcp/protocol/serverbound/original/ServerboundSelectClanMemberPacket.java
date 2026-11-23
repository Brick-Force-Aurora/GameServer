package de.brickforceaurora.server.net.tcp.protocol.serverbound.original;

import de.brickforceaurora.server.net.tcp.protocol.IServerboundPacket;
import de.brickforceaurora.server.net.tcp.protocol.PacketBuf;

public final class ServerboundSelectClanMemberPacket implements IServerboundPacket {

    private int clan;

    public ServerboundSelectClanMemberPacket clan(final int clan) {
        this.clan = clan;
        return this;
    }

    public int clan() {
        return this.clan;
    }

    @Override
    public int packetId() {
        return 200;
    }

    @Override
    public void read(final PacketBuf buf) {
        this.clan = buf.readInt();
    }
}