package de.brickforceaurora.server.net.tcp.protocol.clientbound.original;

import de.brickforceaurora.server.net.tcp.protocol.IClientboundPacket;
import de.brickforceaurora.server.net.tcp.protocol.PacketBuf;

public final class ClientboundDeathCountPacket implements IClientboundPacket {

    private int clientId;
    private int death;

    public ClientboundDeathCountPacket clientId(final int clientId) {
        this.clientId = clientId;
        return this;
    }

    public int clientId() {
        return this.clientId;
    }

    public ClientboundDeathCountPacket death(final int death) {
        this.death = death;
        return this;
    }

    public int death() {
        return this.death;
    }

    @Override
    public int packetId() {
        return 68;
    }

    @Override
    public void write(final PacketBuf buf) {
        buf.writeInt(this.clientId);
        buf.writeInt(this.death);
    }
}
