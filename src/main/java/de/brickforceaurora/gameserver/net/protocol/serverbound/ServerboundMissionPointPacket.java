package de.brickforceaurora.gameserver.net.protocol.serverbound;

import de.brickforceaurora.gameserver.net.protocol.IServerboundPacket;
import io.netty.buffer.ByteBuf;

public final class ServerboundMissionPointPacket implements IServerboundPacket {

    private int redPoint;
    private int bluePoint;

    public ServerboundMissionPointPacket redPoint(final int redPoint) {
        this.redPoint = redPoint;
        return this;
    }

    public int redPoint() {
        return this.redPoint;
    }

    public ServerboundMissionPointPacket bluePoint(final int bluePoint) {
        this.bluePoint = bluePoint;
        return this;
    }

    public int bluePoint() {
        return this.bluePoint;
    }

    @Override
    public int packetId() {
        return 508;
    }

    @Override
    public void read(final ByteBuf buffer) {
        this.redPoint = buffer.readIntLE();
        this.bluePoint = buffer.readIntLE();
    }
}