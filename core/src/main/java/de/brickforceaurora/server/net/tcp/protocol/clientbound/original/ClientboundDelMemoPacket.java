package de.brickforceaurora.server.net.tcp.protocol.clientbound.original;

import de.brickforceaurora.server.net.tcp.protocol.IClientboundPacket;
import de.brickforceaurora.server.net.tcp.protocol.PacketBuf;

public final class ClientboundDelMemoPacket implements IClientboundPacket {

    private long val;

    public ClientboundDelMemoPacket val(final long val) {
        this.val = val;
        return this;
    }

    public long val() {
        return this.val;
    }

    @Override
    public int packetId() {
        return 131;
    }

    @Override
    public void write(final PacketBuf buf) {
        buf.writeLong(this.val);
    }
}