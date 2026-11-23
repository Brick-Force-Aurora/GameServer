package de.brickforceaurora.server.net.tcp.protocol.clientbound.original;

import de.brickforceaurora.server.net.tcp.protocol.IClientboundPacket;
import de.brickforceaurora.server.net.tcp.protocol.PacketBuf;

public final class ClientboundUnpackBundlePacket implements IClientboundPacket {

    private int val;

    public ClientboundUnpackBundlePacket val(final int val) {
        this.val = val;
        return this;
    }

    public int val() {
        return this.val;
    }

    @Override
    public int packetId() {
        return 361;
    }

    @Override
    public void write(final PacketBuf buf) {
        buf.writeInt(this.val);
    }
}