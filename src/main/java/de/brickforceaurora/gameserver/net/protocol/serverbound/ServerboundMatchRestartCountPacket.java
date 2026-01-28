package de.brickforceaurora.gameserver.net.protocol.serverbound;

import de.brickforceaurora.gameserver.net.protocol.IServerboundPacket;
import io.netty.buffer.ByteBuf;

public final class ServerboundMatchRestartCountPacket implements IServerboundPacket {

    private int count;

    public ServerboundMatchRestartCountPacket count(final int count) {
        this.count = count;
        return this;
    }

    public int count() {
        return this.count;
    }

    @Override
    public int packetId() {
        return 264;
    }

    @Override
    public void read(final ByteBuf buffer) {
        this.count = buffer.readIntLE();
    }
}