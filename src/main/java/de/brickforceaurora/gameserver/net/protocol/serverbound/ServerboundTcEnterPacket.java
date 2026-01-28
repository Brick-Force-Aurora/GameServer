package de.brickforceaurora.gameserver.net.protocol.serverbound;

import de.brickforceaurora.gameserver.net.protocol.IServerboundPacket;
import io.netty.buffer.ByteBuf;

public final class ServerboundTcEnterPacket implements IServerboundPacket {

    private int chest;

    public ServerboundTcEnterPacket chest(final int chest) {
        this.chest = chest;
        return this;
    }

    public int chest() {
        return this.chest;
    }

    @Override
    public int packetId() {
        return 372;
    }

    @Override
    public void read(final ByteBuf buffer) {
        this.chest = buffer.readIntLE();
    }
}