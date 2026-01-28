package de.brickforceaurora.gameserver.net.protocol.serverbound;

import de.brickforceaurora.gameserver.net.protocol.IServerboundPacket;
import io.netty.buffer.ByteBuf;

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
    public void read(final ByteBuf buffer) {
        this.clan = buffer.readIntLE();
        this.index = buffer.readIntLE();
    }
}