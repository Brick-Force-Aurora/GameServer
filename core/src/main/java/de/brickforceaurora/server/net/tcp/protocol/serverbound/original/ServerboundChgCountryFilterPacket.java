package de.brickforceaurora.server.net.tcp.protocol.serverbound.original;

import de.brickforceaurora.server.net.tcp.protocol.IServerboundPacket;
import de.brickforceaurora.server.net.tcp.protocol.PacketBuf;

public final class ServerboundChgCountryFilterPacket implements IServerboundPacket {

    private int country;

    public ServerboundChgCountryFilterPacket country(final int country) {
        this.country = country;
        return this;
    }

    public int country() {
        return this.country;
    }

    @Override
    public int packetId() {
        return 348;
    }

    @Override
    public void read(final PacketBuf buf) {
        this.country = buf.readInt();
    }
}