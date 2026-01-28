package de.brickforceaurora.gameserver.net.protocol.serverbound;

import de.brickforceaurora.gameserver.net.protocol.IServerboundPacket;
import io.netty.buffer.ByteBuf;

public final class ServerboundStartPacket implements IServerboundPacket {

    private int remain;

    public ServerboundStartPacket remain(final int remain) {
        this.remain = remain;
        return this;
    }

    public int remain() {
        return this.remain;
    }

    @Override
    public int packetId() {
        return 49;
    }

    @Override
    public void read(final ByteBuf buffer) {
        this.remain = buffer.readIntLE();
    }
}