package de.brickforceaurora.server.net.tcp.protocol.serverbound.original;

import de.brickforceaurora.server.net.tcp.protocol.IServerboundPacket;
import de.brickforceaurora.server.net.tcp.protocol.PacketBuf;

public final class ServerboundCreateClanPacket implements IServerboundPacket {

    private String clanName;

    public ServerboundCreateClanPacket clanName(final String clanName) {
        this.clanName = clanName;
        return this;
    }

    public String clanName() {
        return this.clanName;
    }

    @Override
    public int packetId() {
        return 189;
    }

    @Override
    public void read(final PacketBuf buf) {
        this.clanName = buf.readString();
    }
}