package de.brickforceaurora.server.net.protocol.serverbound.original;

import de.brickforceaurora.server.net.protocol.IServerboundPacket;
import de.brickforceaurora.server.net.protocol.PacketBuf;

public final class ServerboundClanMatchTeamGetbackPacket implements IServerboundPacket {

    private int clan;
    private int index;

    public ServerboundClanMatchTeamGetbackPacket clan(final int clan) {
        this.clan = clan;
        return this;
    }

    public int clan() {
        return this.clan;
    }

    public ServerboundClanMatchTeamGetbackPacket index(final int index) {
        this.index = index;
        return this;
    }

    public int index() {
        return this.index;
    }

    @Override
    public int packetId() {
        return 275;
    }

    @Override
    public void read(final PacketBuf buf) {
        this.clan = buf.readInt();
        this.index = buf.readInt();
    }
}