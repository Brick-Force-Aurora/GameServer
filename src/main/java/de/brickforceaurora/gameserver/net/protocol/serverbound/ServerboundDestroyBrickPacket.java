package de.brickforceaurora.gameserver.net.protocol.serverbound;

import de.brickforceaurora.gameserver.net.protocol.IServerboundPacket;
import io.netty.buffer.ByteBuf;

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
    public void read(final ByteBuf buffer) {
        this.brick = buffer.readIntLE();
    }
}