package de.brickforceaurora.server.net.tcp.protocol.serverbound.original;

import de.brickforceaurora.server.net.tcp.protocol.IServerboundPacket;
import de.brickforceaurora.server.net.tcp.protocol.PacketBuf;

public final class ServerboundDelFriendPacket implements IServerboundPacket {

    private int clientId;

    public ServerboundDelFriendPacket clientId(final int clientId) {
        this.clientId = clientId;
        return this;
    }

    public int clientId() {
        return this.clientId;
    }

    @Override
    public int packetId() {
        return 107;
    }

    @Override
    public void read(final PacketBuf buf) {
        this.clientId = buf.readInt();
    }
}