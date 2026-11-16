package de.brickforceaurora.server.net.protocol.clientbound.original;

import de.brickforceaurora.server.net.protocol.IClientboundPacket;
import de.brickforceaurora.server.net.protocol.PacketBuf;

public final class ClientboundMutePacket implements IClientboundPacket {

    private String val;
    private int val2;

    public ClientboundMutePacket val(final String val) {
        this.val = val;
        return this;
    }

    public String val() {
        return this.val;
    }

    public ClientboundMutePacket val2(final int val2) {
        this.val2 = val2;
        return this;
    }

    public int val2() {
        return this.val2;
    }

    @Override
    public int packetId() {
        return 456;
    }

    @Override
    public void write(final PacketBuf buf) {
        buf.writeString(this.val);
        buf.writeInt(this.val2);
    }
}