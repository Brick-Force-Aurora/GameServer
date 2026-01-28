package de.brickforceaurora.gameserver.net.protocol.serverbound;

import de.brickforceaurora.gameserver.net.protocol.IServerboundPacket;
import io.netty.buffer.ByteBuf;

public final class ServerboundCreateSquadPacket implements IServerboundPacket {

    private int clan;
    private int wannaPlanyMap;
    private int wannaPlayMode;
    private int maxMember;

    public ServerboundCreateSquadPacket clan(final int clan) {
        this.clan = clan;
        return this;
    }

    public int clan() {
        return this.clan;
    }

    public ServerboundCreateSquadPacket wannaPlanyMap(final int wannaPlanyMap) {
        this.wannaPlanyMap = wannaPlanyMap;
        return this;
    }

    public int wannaPlanyMap() {
        return this.wannaPlanyMap;
    }

    public ServerboundCreateSquadPacket wannaPlayMode(final int wannaPlayMode) {
        this.wannaPlayMode = wannaPlayMode;
        return this;
    }

    public int wannaPlayMode() {
        return this.wannaPlayMode;
    }

    public ServerboundCreateSquadPacket maxMember(final int maxMember) {
        this.maxMember = maxMember;
        return this;
    }

    public int maxMember() {
        return this.maxMember;
    }

    @Override
    public int packetId() {
        return 237;
    }

    @Override
    public void read(final ByteBuf buffer) {
        this.clan = buffer.readIntLE();
        this.wannaPlanyMap = buffer.readIntLE();
        this.wannaPlayMode = buffer.readIntLE();
        this.maxMember = buffer.readIntLE();
    }
}