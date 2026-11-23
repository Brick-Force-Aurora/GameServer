package de.brickforceaurora.server.net.tcp.protocol.serverbound.original;

import de.brickforceaurora.server.net.tcp.protocol.IServerboundPacket;
import de.brickforceaurora.server.net.tcp.protocol.PacketBuf;

public final class ServerboundKickPacket implements IServerboundPacket {

    private int clientId;

    public ServerboundKickPacket clientId(final int clientId) {
        this.clientId = clientId;
        return this;
    }

    public int clientId() {
        return this.clientId;
    }

    @Override
    public int packetId() {
        return 88;
    }

    @Override
    public void read(final PacketBuf buf) {
        this.clientId = buf.readInt();
    }
}