package de.brickforceaurora.server.net.tcp.protocol.clientbound.original;

import de.brickforceaurora.server.net.tcp.protocol.IClientboundPacket;
import de.brickforceaurora.server.net.tcp.protocol.PacketBuf;

public final class ClientboundChargePicknwinCoinPacket implements IClientboundPacket {

    private int val;
    private long Unnamed0;
    private String val3;
    private int val4;

    public ClientboundChargePicknwinCoinPacket val(final int val) {
        this.val = val;
        return this;
    }

    public int val() {
        return this.val;
    }

    public ClientboundChargePicknwinCoinPacket Unnamed0(final long Unnamed0) {
        this.Unnamed0 = Unnamed0;
        return this;
    }

    public long Unnamed0() {
        return this.Unnamed0;
    }

    public ClientboundChargePicknwinCoinPacket val3(final String val3) {
        this.val3 = val3;
        return this;
    }

    public String val3() {
        return this.val3;
    }

    public ClientboundChargePicknwinCoinPacket val4(final int val4) {
        this.val4 = val4;
        return this;
    }

    public int val4() {
        return this.val4;
    }

    @Override
    public int packetId() {
        return 398;
    }

    @Override
    public void write(final PacketBuf buf) {
        buf.writeInt(this.val);
        buf.writeLong(this.Unnamed0);
        buf.writeString(this.val3);
        buf.writeInt(this.val4);
    }
}