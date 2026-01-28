package de.brickforceaurora.gameserver.net.protocol.serverbound;

import de.brickforceaurora.gameserver.net.protocol.IServerboundPacket;
import io.netty.buffer.ByteBuf;

public final class ServerboundSetStatusPacket implements IServerboundPacket {

    private int status;

    public ServerboundSetStatusPacket status(final int status) {
        this.status = status;
        return this;
    }

    public int status() {
        return this.status;
    }

    @Override
    public int packetId() {
        return 47;
    }

    @Override
    public void read(final ByteBuf buffer) {
        this.status = buffer.readIntLE();
    }
}