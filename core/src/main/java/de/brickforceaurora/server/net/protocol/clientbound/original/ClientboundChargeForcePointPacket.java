package de.brickforceaurora.server.net.protocol.clientbound.original;

import de.brickforceaurora.server.net.protocol.IClientboundPacket;
import de.brickforceaurora.server.net.protocol.PacketBuf;

public final class ClientboundChargeForcePointPacket implements IClientboundPacket {

    private int val;
    private long Unnamed0;
    private String val3;
    private int val4;

    public ClientboundChargeForcePointPacket val(final int val) {
        this.val = val;
        return this;
    }

    public int val() {
        return this.val;
    }

    public ClientboundChargeForcePointPacket Unnamed0(final long Unnamed0) {
        this.Unnamed0 = Unnamed0;
        return this;
    }

    public long Unnamed0() {
        return this.Unnamed0;
    }

    public ClientboundChargeForcePointPacket val3(final String val3) {
        this.val3 = val3;
        return this;
    }

    public String val3() {
        return this.val3;
    }

    public ClientboundChargeForcePointPacket val4(final int val4) {
        this.val4 = val4;
        return this;
    }

    public int val4() {
        return this.val4;
    }

    @Override
    public int packetId() {
        return 472;
    }

    @Override
    public void write(final PacketBuf buf) {
        buf.writeInt(this.val);
        buf.writeLong(this.Unnamed0);
        buf.writeString(this.val3);
        buf.writeInt(this.val4);
    }
}