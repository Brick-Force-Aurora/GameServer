package de.brickforceaurora.server.net.protocol.clientbound.original;

import de.brickforceaurora.server.net.protocol.IClientboundPacket;
import de.brickforceaurora.server.net.protocol.PacketBuf;

public final class ClientboundClanChangeRoomNamePacket implements IClientboundPacket {

    private String val;

    public ClientboundClanChangeRoomNamePacket val(final String val) {
        this.val = val;
        return this;
    }

    public String val() {
        return this.val;
    }

    @Override
    public int packetId() {
        return 564;
    }

    @Override
    public void write(final PacketBuf buf) {
        buf.writeString(this.val);
    }
}