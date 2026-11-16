package de.brickforceaurora.server.net.protocol.serverbound.original;

import de.brickforceaurora.server.net.protocol.IServerboundPacket;
import de.brickforceaurora.server.net.protocol.PacketBuf;

public final class ServerboundApplyClanPacket implements IServerboundPacket {

    private int clan;
    private String clanName;

    public ServerboundApplyClanPacket clan(final int clan) {
        this.clan = clan;
        return this;
    }

    public int clan() {
        return this.clan;
    }

    public ServerboundApplyClanPacket clanName(final String clanName) {
        this.clanName = clanName;
        return this;
    }

    public String clanName() {
        return this.clanName;
    }

    @Override
    public int packetId() {
        return 221;
    }

    @Override
    public void read(final PacketBuf buf) {
        this.clan = buf.readInt();
        this.clanName = buf.readString();
    }
}