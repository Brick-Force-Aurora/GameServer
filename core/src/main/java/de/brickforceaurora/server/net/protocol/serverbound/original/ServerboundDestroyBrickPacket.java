package de.brickforceaurora.server.net.protocol.serverbound.original;

import de.brickforceaurora.server.net.protocol.IServerboundPacket;
import de.brickforceaurora.server.net.protocol.PacketBuf;

public final class ServerboundDestroyBrickPacket implements IServerboundPacket {

    private int brick;

    public ServerboundDestroyBrickPacket brick(final int brick) {
        this.brick = brick;
        return this;
    }

    public int brick() {
        return this.brick;
    }

    @Override
    public int packetId() {
        return 76;
    }

    @Override
    public void read(final PacketBuf buf) {
        this.brick = buf.readInt();
    }
}