package de.brickforceaurora.server.net.tcp.protocol.clientbound.original;

import de.brickforceaurora.server.net.tcp.protocol.IClientboundPacket;
import de.brickforceaurora.server.net.tcp.protocol.PacketBuf;

public final class ClientboundBmBlastPacket implements IClientboundPacket {

    private int Unnamed0;
    private int Unnamed1;

    public ClientboundBmBlastPacket Unnamed0(final int Unnamed0) {
        this.Unnamed0 = Unnamed0;
        return this;
    }

    public int Unnamed0() {
        return this.Unnamed0;
    }

    public ClientboundBmBlastPacket Unnamed1(final int Unnamed1) {
        this.Unnamed1 = Unnamed1;
        return this;
    }

    public int Unnamed1() {
        return this.Unnamed1;
    }

    @Override
    public int packetId() {
        return 284;
    }

    @Override
    public void write(final PacketBuf buf) {
        buf.writeInt(this.Unnamed0);
        buf.writeInt(this.Unnamed1);
    }
}