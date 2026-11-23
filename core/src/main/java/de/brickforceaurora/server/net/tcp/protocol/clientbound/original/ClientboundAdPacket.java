package de.brickforceaurora.server.net.tcp.protocol.clientbound.original;

import de.brickforceaurora.server.net.tcp.protocol.IClientboundPacket;
import de.brickforceaurora.server.net.tcp.protocol.PacketBuf;

public final class ClientboundAdPacket implements IClientboundPacket {

    private int val;
    private int val2;
    private String val3;
    private int val4;
    private String val5;

    public ClientboundAdPacket val(final int val) {
        this.val = val;
        return this;
    }

    public int val() {
        return this.val;
    }

    public ClientboundAdPacket val2(final int val2) {
        this.val2 = val2;
        return this;
    }

    public int val2() {
        return this.val2;
    }

    public ClientboundAdPacket val3(final String val3) {
        this.val3 = val3;
        return this;
    }

    public String val3() {
        return this.val3;
    }

    public ClientboundAdPacket val4(final int val4) {
        this.val4 = val4;
        return this;
    }

    public int val4() {
        return this.val4;
    }

    public ClientboundAdPacket val5(final String val5) {
        this.val5 = val5;
        return this;
    }

    public String val5() {
        return this.val5;
    }

    @Override
    public int packetId() {
        return 347;
    }

    @Override
    public void write(final PacketBuf buf) {
        buf.writeInt(this.val);
        buf.writeInt(this.val2);
        buf.writeString(this.val3);
        buf.writeInt(this.val4);
        buf.writeString(this.val5);
    }
}