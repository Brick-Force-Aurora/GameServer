package de.brickforceaurora.server.net.tcp.protocol.clientbound.original;

import de.brickforceaurora.server.net.tcp.protocol.IClientboundPacket;
import de.brickforceaurora.server.net.tcp.protocol.PacketBuf;

public final class ClientboundTeamChangeFailPacket implements IClientboundPacket {

    private int reason;

    public ClientboundTeamChangeFailPacket reason(final int reason) {
        this.reason = reason;
        return this;
    }

    public int reason() {
        return this.reason;
    }

    @Override
    public int packetId() {
        return 87;
    }

    @Override
    public void write(final PacketBuf buf) {
        buf.writeInt(this.reason);
    }
}