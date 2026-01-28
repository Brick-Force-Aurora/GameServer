package de.brickforceaurora.gameserver.net.protocol.serverbound;

import de.brickforceaurora.gameserver.net.protocol.IServerboundPacket;
import io.netty.buffer.ByteBuf;

public final class ServerboundBmBlastPacket implements IServerboundPacket {

    private int bomb;

    public ServerboundBmBlastPacket bomb(final int bomb) {
        this.bomb = bomb;
        return this;
    }

    public int bomb() {
        return this.bomb;
    }

    @Override
    public int packetId() {
        return 283;
    }

    @Override
    public void read(final ByteBuf buffer) {
        this.bomb = buffer.readIntLE();
    }
}