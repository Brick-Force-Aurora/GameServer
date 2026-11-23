package de.brickforceaurora.server.net.tcp.protocol.clientbound.original;

import de.brickforceaurora.server.net.tcp.protocol.IClientboundPacket;
import de.brickforceaurora.server.net.tcp.protocol.PacketBuf;

public final class ClientboundTcOpenPrizeTagPacket implements IClientboundPacket {

    private long val;
    private int Unnamed0;
    private int val3;
    private int val4;
    private boolean val5;
    private boolean val6;

    public ClientboundTcOpenPrizeTagPacket val(final long val) {
        this.val = val;
        return this;
    }

    public long val() {
        return this.val;
    }

    public ClientboundTcOpenPrizeTagPacket Unnamed0(final int Unnamed0) {
        this.Unnamed0 = Unnamed0;
        return this;
    }

    public int Unnamed0() {
        return this.Unnamed0;
    }

    public ClientboundTcOpenPrizeTagPacket val3(final int val3) {
        this.val3 = val3;
        return this;
    }

    public int val3() {
        return this.val3;
    }

    public ClientboundTcOpenPrizeTagPacket val4(final int val4) {
        this.val4 = val4;
        return this;
    }

    public int val4() {
        return this.val4;
    }

    public ClientboundTcOpenPrizeTagPacket val5(final boolean val5) {
        this.val5 = val5;
        return this;
    }

    public boolean val5() {
        return this.val5;
    }

    public ClientboundTcOpenPrizeTagPacket val6(final boolean val6) {
        this.val6 = val6;
        return this;
    }

    public boolean val6() {
        return this.val6;
    }

    @Override
    public int packetId() {
        return 376;
    }

    @Override
    public void write(final PacketBuf buf) {
        buf.writeLong(this.val);
        buf.writeInt(this.Unnamed0);
        buf.writeInt(this.val3);
        buf.writeInt(this.val4);
        buf.writeBoolean(this.val5);
        buf.writeBoolean(this.val6);
    }
}