package de.brickforceaurora.gameserver.net.protocol.serverbound;

import de.brickforceaurora.gameserver.net.protocol.IServerboundPacket;
import io.netty.buffer.ByteBuf;

public final class ServerboundLoadCompletePacket implements IServerboundPacket {

    private int crc;

    public ServerboundLoadCompletePacket crc(final int crc) {
        this.crc = crc;
        return this;
    }

    public int crc() {
        return this.crc;
    }

    @Override
    public int packetId() {
        return 42;
    }

    @Override
    public void read(final ByteBuf buffer) {
        this.crc = buffer.readIntLE();
    }
}