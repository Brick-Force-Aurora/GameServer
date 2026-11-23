package de.brickforceaurora.server.net.tcp.protocol.serverbound.original;

import de.brickforceaurora.server.net.tcp.protocol.IServerboundPacket;
import de.brickforceaurora.server.net.tcp.protocol.PacketBuf;

public final class ServerboundCheckClanNameAvailabilityPacket implements IServerboundPacket {

    private String clanName;

    public ServerboundCheckClanNameAvailabilityPacket clanName(final String clanName) {
        this.clanName = clanName;
        return this;
    }

    public String clanName() {
        return this.clanName;
    }

    @Override
    public int packetId() {
        return 187;
    }

    @Override
    public void read(final PacketBuf buf) {
        this.clanName = buf.readString();
    }
}