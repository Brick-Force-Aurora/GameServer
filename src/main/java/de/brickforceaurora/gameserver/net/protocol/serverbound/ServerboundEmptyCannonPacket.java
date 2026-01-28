package de.brickforceaurora.gameserver.net.protocol.serverbound;

import de.brickforceaurora.gameserver.net.protocol.IServerboundPacket;
import io.netty.buffer.ByteBuf;

public final class ServerboundEmptyCannonPacket implements IServerboundPacket {

    private int cannon;

    public ServerboundEmptyCannonPacket cannon(final int cannon) {
        this.cannon = cannon;
        return this;
    }

    public int cannon() {
        return this.cannon;
    }

    @Override
    public int packetId() {
        return 160;
    }

    @Override
    public void read(final ByteBuf buffer) {
        this.cannon = buffer.readIntLE();
    }
}