package de.brickforceaurora.gameserver.net.protocol.serverbound;

import de.brickforceaurora.gameserver.net.protocol.IServerboundPacket;
import io.netty.buffer.ByteBuf;

public final class ServerboundRegMapInfoPacket implements IServerboundPacket {

    private int map;

    public ServerboundRegMapInfoPacket map(final int map) {
        this.map = map;
        return this;
    }

    public int map() {
        return this.map;
    }

    @Override
    public int packetId() {
        return 337;
    }

    @Override
    public void read(final ByteBuf buffer) {
        this.map = buffer.readIntLE();
    }
}