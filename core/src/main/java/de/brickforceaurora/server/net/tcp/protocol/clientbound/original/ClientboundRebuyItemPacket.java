package de.brickforceaurora.server.net.tcp.protocol.clientbound.original;

import de.brickforceaurora.server.net.tcp.protocol.IClientboundPacket;
import de.brickforceaurora.server.net.tcp.protocol.PacketBuf;

public final class ClientboundRebuyItemPacket implements IClientboundPacket {

    private long val;
    private String val2;
    private int val3;
    private int val4;

    public ClientboundRebuyItemPacket val(final long val) {
        this.val = val;
        return this;
    }

    public long val() {
        return this.val;
    }

    public ClientboundRebuyItemPacket val2(final String val2) {
        this.val2 = val2;
        return this;
    }

    public String val2() {
        return this.val2;
    }

    public ClientboundRebuyItemPacket val3(final int val3) {
        this.val3 = val3;
        return this;
    }

    public int val3() {
        return this.val3;
    }

    public ClientboundRebuyItemPacket val4(final int val4) {
        this.val4 = val4;
        return this;
    }

    public int val4() {
        return this.val4;
    }

    @Override
    public int packetId() {
        return 310;
    }

    @Override
    public void write(final PacketBuf buf) {
        buf.writeLong(this.val);
        buf.writeString(this.val2);
        buf.writeInt(this.val3);
        buf.writeInt(this.val4);
    }
}