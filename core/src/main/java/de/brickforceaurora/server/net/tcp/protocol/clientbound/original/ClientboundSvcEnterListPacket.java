package de.brickforceaurora.server.net.tcp.protocol.clientbound.original;

import de.brickforceaurora.server.net.tcp.protocol.IClientboundPacket;
import de.brickforceaurora.server.net.tcp.protocol.PacketBuf;
import de.brickforceaurora.server.net.tcp.protocol.data.api.IClientInfo;

public final class ClientboundSvcEnterListPacket implements IClientboundPacket {

    private IClientInfo[] clients;

    public ClientboundSvcEnterListPacket clients(final IClientInfo[] clients) {
        this.clients = clients;
        return this;
    }

    public IClientInfo[] clients() {
        return this.clients;
    }

    @Override
    public int packetId() {
        return 467;
    }

    @Override
    public void write(final PacketBuf buf) {
        buf.writeArray(clients, IClientInfo::toBuffer);
    }
}