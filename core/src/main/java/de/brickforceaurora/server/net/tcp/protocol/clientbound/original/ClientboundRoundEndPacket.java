package de.brickforceaurora.server.net.tcp.protocol.clientbound.original;

import de.brickforceaurora.server.net.tcp.protocol.IClientboundPacket;
import de.brickforceaurora.server.net.tcp.protocol.PacketBuf;

public final class ClientboundRoundEndPacket implements IClientboundPacket {

    private int val;
    private byte val2;
    private byte val3;
    private byte val4;

    public ClientboundRoundEndPacket val(final int val) {
        this.val = val;
        return this;
    }

    public int val() {
        return this.val;
    }

    public ClientboundRoundEndPacket val2(final byte val2) {
        this.val2 = val2;
        return this;
    }

    public byte val2() {
        return this.val2;
    }

    public ClientboundRoundEndPacket val3(final byte val3) {
        this.val3 = val3;
        return this;
    }

    public byte val3() {
        return this.val3;
    }

    public ClientboundRoundEndPacket val4(final byte val4) {
        this.val4 = val4;
        return this;
    }

    public byte val4() {
        return this.val4;
    }

    @Override
    public int packetId() {
        return 205;
    }

    @Override
    public void write(final PacketBuf buf) {
        buf.writeInt(this.val);
        buf.writeByte(this.val2);
        buf.writeByte(this.val3);
        buf.writeByte(this.val4);
    }
}