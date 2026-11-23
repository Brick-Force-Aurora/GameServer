package de.brickforceaurora.server.net.tcp.protocol.clientbound.original;

import de.brickforceaurora.server.net.tcp.protocol.IClientboundPacket;
import de.brickforceaurora.server.net.tcp.protocol.PacketBuf;

public final class ClientboundDelFriendFailPacket implements IClientboundPacket {

    private int Unnamed0;

    public ClientboundDelFriendFailPacket Unnamed0(final int Unnamed0) {
        this.Unnamed0 = Unnamed0;
        return this;
    }

    public int Unnamed0() {
        return this.Unnamed0;
    }

    @Override
    public int packetId() {
        return 114;
    }

    @Override
    public void write(final PacketBuf buf) {
        buf.writeInt(this.Unnamed0);
    }
}