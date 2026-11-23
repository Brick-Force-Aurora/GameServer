package de.brickforceaurora.server.net.tcp.protocol.clientbound.original;

import de.brickforceaurora.server.net.tcp.protocol.IClientboundPacket;
import de.brickforceaurora.server.net.tcp.protocol.PacketBuf;

public final class ClientboundCurChannelPacket implements IClientboundPacket {

    private int curChannelId;

    public ClientboundCurChannelPacket curChannelId(final int curChannelId) {
        this.curChannelId = curChannelId;
        return this;
    }

    public int curChannelId() {
        return this.curChannelId;
    }

    @Override
    public int packetId() {
        return 147;
    }

    @Override
    public void write(final PacketBuf buf) {
        buf.writeInt(this.curChannelId);
    }
}