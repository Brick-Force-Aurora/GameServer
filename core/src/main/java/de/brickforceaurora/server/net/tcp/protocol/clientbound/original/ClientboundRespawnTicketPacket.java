package de.brickforceaurora.server.net.tcp.protocol.clientbound.original;

import de.brickforceaurora.server.net.tcp.protocol.IClientboundPacket;
import de.brickforceaurora.server.net.tcp.protocol.PacketBuf;

public final class ClientboundRespawnTicketPacket implements IClientboundPacket {

    private int ticket;

    public ClientboundRespawnTicketPacket ticket(final int ticket) {
        this.ticket = ticket;
        return this;
    }

    public int ticket() {
        return this.ticket;
    }

    @Override
    public int packetId() {
        return 64;
    }

    @Override
    public void write(final PacketBuf buf) {
        buf.writeInt(this.ticket);
    }
}