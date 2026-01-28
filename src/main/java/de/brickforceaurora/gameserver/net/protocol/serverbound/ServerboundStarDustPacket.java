package de.brickforceaurora.gameserver.net.protocol.serverbound;

import de.brickforceaurora.gameserver.net.protocol.IServerboundPacket;
import io.netty.buffer.ByteBuf;

public final class ServerboundStarDustPacket implements IServerboundPacket {

    private int map;
    private int starDust;

    public ServerboundStarDustPacket map(final int map) {
        this.map = map;
        return this;
    }

    public int map() {
        return this.map;
    }

    public ServerboundStarDustPacket starDust(final int starDust) {
        this.starDust = starDust;
        return this;
    }

    public int starDust() {
        return this.starDust;
    }

    @Override
    public int packetId() {
        return 60;
    }

    @Override
    public void read(final ByteBuf buffer) {
        this.map = buffer.readIntLE();
        this.starDust = buffer.readIntLE();
    }
}