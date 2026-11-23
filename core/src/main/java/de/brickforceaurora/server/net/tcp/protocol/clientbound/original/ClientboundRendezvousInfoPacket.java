package de.brickforceaurora.server.net.tcp.protocol.clientbound.original;

import de.brickforceaurora.server.net.tcp.protocol.IClientboundPacket;
import de.brickforceaurora.server.net.tcp.protocol.PacketBuf;

public final class ClientboundRendezvousInfoPacket implements IClientboundPacket {

    private String ip;
    private int port;

    public ClientboundRendezvousInfoPacket ip(final String ip) {
        this.ip = ip;
        return this;
    }

    public String ip() {
        return this.ip;
    }

    public ClientboundRendezvousInfoPacket port(final int port) {
        this.port = port;
        return this;
    }

    public int port() {
        return this.port;
    }

    @Override
    public int packetId() {
        return 320;
    }

    @Override
    public void write(final PacketBuf buf) {
        buf.writeInt(0); //unused
        buf.writeString(this.ip);
        buf.writeInt(this.port);
    }
}