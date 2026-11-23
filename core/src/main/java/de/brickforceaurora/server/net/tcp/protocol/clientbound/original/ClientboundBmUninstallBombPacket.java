package de.brickforceaurora.server.net.tcp.protocol.clientbound.original;

import de.brickforceaurora.server.net.tcp.protocol.IClientboundPacket;
import de.brickforceaurora.server.net.tcp.protocol.PacketBuf;

public final class ClientboundBmUninstallBombPacket implements IClientboundPacket {

    private int val;
    private int Unnamed0;

    public ClientboundBmUninstallBombPacket val(final int val) {
        this.val = val;
        return this;
    }

    public int val() {
        return this.val;
    }

    public ClientboundBmUninstallBombPacket Unnamed0(final int Unnamed0) {
        this.Unnamed0 = Unnamed0;
        return this;
    }

    public int Unnamed0() {
        return this.Unnamed0;
    }

    @Override
    public int packetId() {
        return 282;
    }

    @Override
    public void write(final PacketBuf buf) {
        buf.writeInt(this.val);
        buf.writeInt(this.Unnamed0);
    }
}