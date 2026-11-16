package de.brickforceaurora.server.net.protocol.serverbound.original;

import de.brickforceaurora.server.net.protocol.IServerboundPacket;
import de.brickforceaurora.server.net.protocol.PacketBuf;

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
    public void read(final PacketBuf buf) {
        this.map = buf.readInt();
        this.starDust = buf.readInt();
    }
}