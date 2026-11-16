package de.brickforceaurora.server.net.protocol.serverbound.original;

import de.brickforceaurora.server.net.protocol.IServerboundPacket;
import de.brickforceaurora.server.net.protocol.PacketBuf;
import de.brickforceaurora.server.net.protocol.data.ClientStatus;

public final class ServerboundSetStatusPacket implements IServerboundPacket {

    private ClientStatus status;

    public ClientStatus status() {
        return this.status;
    }

    @Override
    public int packetId() {
        return 47;
    }

    @Override
    public void read(final PacketBuf buf) {
        this.status = ClientStatus.byId(buf.readInt());
    }
}