package de.brickforceaurora.gameserver.net.protocol.serverbound;

import de.brickforceaurora.gameserver.net.protocol.IServerboundPacket;
import io.netty.buffer.ByteBuf;

public final class ServerboundJoinSquadPacket implements IServerboundPacket {

    private int clan;
    private int index;
    private int squadCounter;

    public ServerboundJoinSquadPacket clan(final int clan) {
        this.clan = clan;
        return this;
    }

    public int clan() {
        return this.clan;
    }

    public ServerboundJoinSquadPacket index(final int index) {
        this.index = index;
        return this;
    }

    public int index() {
        return this.index;
    }

    public ServerboundJoinSquadPacket squadCounter(final int squadCounter) {
        this.squadCounter = squadCounter;
        return this;
    }

    public int squadCounter() {
        return this.squadCounter;
    }

    @Override
    public int packetId() {
        return 239;
    }

    @Override
    public void read(final ByteBuf buffer) {
        this.clan = buffer.readIntLE();
        this.index = buffer.readIntLE();
        this.squadCounter = buffer.readIntLE();
    }
}