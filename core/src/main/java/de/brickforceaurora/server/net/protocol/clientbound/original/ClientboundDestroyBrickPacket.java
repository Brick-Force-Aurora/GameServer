package de.brickforceaurora.server.net.protocol.clientbound.original;

import de.brickforceaurora.server.net.protocol.IClientboundPacket;
import de.brickforceaurora.server.net.protocol.PacketBuf;

public final class ClientboundDestroyBrickPacket implements IClientboundPacket {

    private int brick;

    public ClientboundDestroyBrickPacket brick(final int brick) {
        this.brick = brick;
        return this;
    }

    public int brick() {
        return this.brick;
    }

    @Override
    public int packetId() {
        return 77;
    }

    @Override
    public void write(final PacketBuf buf) {
        buf.writeInt(this.brick);
    }
}
