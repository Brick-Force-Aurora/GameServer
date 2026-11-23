package de.brickforceaurora.server.net.tcp.protocol.clientbound.original;

import de.brickforceaurora.server.net.tcp.protocol.IClientboundPacket;
import de.brickforceaurora.server.net.tcp.protocol.PacketBuf;

public final class ClientboundAutoLoginToRunupPacket implements IClientboundPacket {

    private int val;
    private int val2;
    private boolean val3;

    public ClientboundAutoLoginToRunupPacket val(final int val) {
        this.val = val;
        return this;
    }

    public int val() {
        return this.val;
    }

    public ClientboundAutoLoginToRunupPacket val2(final int val2) {
        this.val2 = val2;
        return this;
    }

    public int val2() {
        return this.val2;
    }

    public ClientboundAutoLoginToRunupPacket val3(final boolean val3) {
        this.val3 = val3;
        return this;
    }

    public boolean val3() {
        return this.val3;
    }

    @Override
    public int packetId() {
        return 410;
    }

    @Override
    public void write(final PacketBuf buf) {
        buf.writeInt(this.val);
        buf.writeInt(this.val2);
        buf.writeBoolean(this.val3);
    }
}