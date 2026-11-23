package de.brickforceaurora.server.net.tcp.protocol.clientbound.original;

import de.brickforceaurora.server.net.tcp.protocol.IClientboundPacket;
import de.brickforceaurora.server.net.tcp.protocol.PacketBuf;

public final class ClientboundRoundScorePacket implements IClientboundPacket {

    private int val;
    private int val2;

    public ClientboundRoundScorePacket val(final int val) {
        this.val = val;
        return this;
    }

    public int val() {
        return this.val;
    }

    public ClientboundRoundScorePacket val2(final int val2) {
        this.val2 = val2;
        return this;
    }

    public int val2() {
        return this.val2;
    }

    @Override
    public int packetId() {
        return 300;
    }

    @Override
    public void write(final PacketBuf buf) {
        buf.writeInt(this.val);
        buf.writeInt(this.val2);
    }
}