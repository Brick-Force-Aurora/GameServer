package de.brickforceaurora.server.net.protocol.clientbound.original;

import de.brickforceaurora.server.net.protocol.IClientboundPacket;
import de.brickforceaurora.server.net.protocol.PacketBuf;

public final class ClientboundKillCountPacket implements IClientboundPacket {

    private int clientId;
    private int kill;

    public ClientboundKillCountPacket clientId(final int clientId) {
        this.clientId = clientId;
        return this;
    }

    public int clientId() {
        return this.clientId;
    }

    public ClientboundKillCountPacket kill(final int kill) {
        this.kill = kill;
        return this;
    }

    public int kill() {
        return this.kill;
    }

    @Override
    public int packetId() {
        return 69;
    }

    @Override
    public void write(final PacketBuf buf) {
        buf.writeInt(this.clientId);
        buf.writeInt(this.kill);
    }
}
