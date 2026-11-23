package de.brickforceaurora.server.net.tcp.protocol.serverbound.original;

import de.brickforceaurora.server.net.tcp.protocol.IServerboundPacket;
import de.brickforceaurora.server.net.tcp.protocol.PacketBuf;

public final class ServerboundZombieObserverPacket implements IServerboundPacket {

    private int clientId;

    public ServerboundZombieObserverPacket clientId(final int clientId) {
        this.clientId = clientId;
        return this;
    }

    public int clientId() {
        return clientId;
    }

    @Override
    public int packetId() {
        return 549;
    }

    @Override
    public void read(final PacketBuf buf) {
        this.clientId = buf.readInt();
    }
}