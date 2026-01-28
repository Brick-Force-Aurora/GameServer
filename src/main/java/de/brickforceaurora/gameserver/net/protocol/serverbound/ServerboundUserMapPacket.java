package de.brickforceaurora.gameserver.net.protocol.serverbound;

import de.brickforceaurora.gameserver.net.protocol.IServerboundPacket;
import io.netty.buffer.ByteBuf;

public final class ServerboundUserMapPacket implements IServerboundPacket {

    private int page;

    public ServerboundUserMapPacket page(final int page) {
        this.page = page;
        return this;
    }

    public int page() {
        return this.page;
    }

    @Override
    public int packetId() {
        return 429;
    }

    @Override
    public void read(final ByteBuf buffer) {
        this.page = buffer.readIntLE();
    }
}