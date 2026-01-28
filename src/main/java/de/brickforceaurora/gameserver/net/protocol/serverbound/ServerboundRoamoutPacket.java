package de.brickforceaurora.gameserver.net.protocol.serverbound;

import de.brickforceaurora.gameserver.net.protocol.IServerboundPacket;
import io.netty.buffer.ByteBuf;

public final class ServerboundRoamoutPacket implements IServerboundPacket {

    private int dst;

    public ServerboundRoamoutPacket dst(final int dst) {
        this.dst = dst;
        return this;
    }

    public int dst() {
        return this.dst;
    }

    @Override
    public int packetId() {
        return 143;
    }

    @Override
    public void read(final ByteBuf buffer) {
        this.dst = buffer.readIntLE();
    }
}