package de.brickforceaurora.server.net.tcp.protocol.clientbound.original;

import de.brickforceaurora.server.net.tcp.protocol.IClientboundPacket;
import de.brickforceaurora.server.net.tcp.protocol.PacketBuf;

public final class ClientboundSendClanInvitationPacket implements IClientboundPacket {

    private long val;
    private String val2;

    public ClientboundSendClanInvitationPacket val(final long val) {
        this.val = val;
        return this;
    }

    public long val() {
        return this.val;
    }

    public ClientboundSendClanInvitationPacket val2(final String val2) {
        this.val2 = val2;
        return this;
    }

    public String val2() {
        return this.val2;
    }

    @Override
    public int packetId() {
        return 194;
    }

    @Override
    public void write(final PacketBuf buf) {
        buf.writeLong(this.val);
        buf.writeString(this.val2);
    }
}