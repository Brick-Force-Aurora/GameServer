package de.brickforceaurora.server.net.tcp.protocol.clientbound.original;

import de.brickforceaurora.server.net.tcp.protocol.IClientboundPacket;
import de.brickforceaurora.server.net.tcp.protocol.PacketBuf;

public final class ClientboundBundlePacket implements IClientboundPacket {

    private int val;
    private String val2;
    private String val3;
    private int val4;

    public ClientboundBundlePacket val(final int val) {
        this.val = val;
        return this;
    }

    public int val() {
        return this.val;
    }

    public ClientboundBundlePacket val2(final String val2) {
        this.val2 = val2;
        return this;
    }

    public String val2() {
        return this.val2;
    }

    public ClientboundBundlePacket val3(final String val3) {
        this.val3 = val3;
        return this;
    }

    public String val3() {
        return this.val3;
    }

    public ClientboundBundlePacket val4(final int val4) {
        this.val4 = val4;
        return this;
    }

    public int val4() {
        return this.val4;
    }

    @Override
    public int packetId() {
        return 359;
    }

    @Override
    public void write(final PacketBuf buf) {
        buf.writeInt(this.val);
        buf.writeString(this.val2);
        buf.writeString(this.val3);
        buf.writeInt(this.val4);
    }
}