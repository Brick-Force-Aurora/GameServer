package de.brickforceaurora.gameserver.net.protocol.serverbound;

import de.brickforceaurora.gameserver.net.protocol.IServerboundPacket;
import io.netty.buffer.ByteBuf;

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
    public void read(final ByteBuf buffer) {
        this.player = buffer.readIntLE();
        this.clan = buffer.readIntLE();
    }
}