package de.brickforceaurora.server.net.tcp.protocol.serverbound.original;

import de.brickforceaurora.server.net.tcp.protocol.IServerboundPacket;
import de.brickforceaurora.server.net.tcp.protocol.PacketBuf;

public final class ServerboundClanMemberPacket implements IServerboundPacket {

    private int player;
    private int clan;

    public ServerboundClanMemberPacket player(final int player) {
        this.player = player;
        return this;
    }

    public int player() {
        return this.player;
    }

    public ServerboundClanMemberPacket clan(final int clan) {
        this.clan = clan;
        return this;
    }

    public int clan() {
        return this.clan;
    }

    @Override
    public int packetId() {
        return 233;
    }

    @Override
    public void read(final PacketBuf buf) {
        this.player = buf.readInt();
        this.clan = buf.readInt();
    }
}