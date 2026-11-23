package de.brickforceaurora.server.net.tcp.protocol.clientbound.original;

import de.brickforceaurora.server.net.tcp.protocol.IClientboundPacket;
import de.brickforceaurora.server.net.tcp.protocol.PacketBuf;

public final class ClientboundAddFriendFailPacket implements IClientboundPacket {

    private int Unnamed0;

    public ClientboundAddFriendFailPacket Unnamed0(final int Unnamed0) {
        this.Unnamed0 = Unnamed0;
        return this;
    }

    public int Unnamed0() {
        return this.Unnamed0;
    }

    @Override
    public int packetId() {
        return 113;
    }

    @Override
    public void write(final PacketBuf buf) {
        buf.writeInt(this.Unnamed0);
    }
}