package de.brickforceaurora.server.net.tcp.protocol.clientbound.original;

import de.brickforceaurora.server.net.tcp.protocol.IClientboundPacket;
import de.brickforceaurora.server.net.tcp.protocol.PacketBuf;

public final class ClientboundStarRatePacket implements IClientboundPacket {

    private int val;
    private String val2;
    private int val3;

    public ClientboundStarRatePacket val(final int val) {
        this.val = val;
        return this;
    }

    public int val() {
        return this.val;
    }

    public ClientboundStarRatePacket val2(final String val2) {
        this.val2 = val2;
        return this;
    }

    public String val2() {
        return this.val2;
    }

    public ClientboundStarRatePacket val3(final int val3) {
        this.val3 = val3;
        return this;
    }

    public int val3() {
        return this.val3;
    }

    @Override
    public int packetId() {
        return 532;
    }

    @Override
    public void write(final PacketBuf buf) {
        buf.writeInt(this.val);
        buf.writeString(this.val2);
        buf.writeInt(this.val3);
    }
}