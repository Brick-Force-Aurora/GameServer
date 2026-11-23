package de.brickforceaurora.server.net.tcp.protocol.serverbound.original;

import de.brickforceaurora.server.net.tcp.protocol.IServerboundPacket;
import de.brickforceaurora.server.net.tcp.protocol.PacketBuf;

public final class ServerboundHashCheckPacket implements IServerboundPacket {

    private String hash;

    public ServerboundHashCheckPacket hash(final String hash) {
        this.hash = hash;
        return this;
    }

    public String hash() {
        return this.hash;
    }

    @Override
    public int packetId() {
        return 395;
    }

    @Override
    public void read(final PacketBuf buf) {
        this.hash = buf.readString();
    }
}