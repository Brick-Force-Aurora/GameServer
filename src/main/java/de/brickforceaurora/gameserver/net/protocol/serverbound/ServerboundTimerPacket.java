package de.brickforceaurora.gameserver.net.protocol.serverbound;

import de.brickforceaurora.gameserver.net.protocol.IServerboundPacket;
import io.netty.buffer.ByteBuf;

public final class ServerboundTimerPacket implements IServerboundPacket {

    private int remainTime;
    private int playTime;

    public ServerboundTimerPacket remainTime(final int remainTime) {
        this.remainTime = remainTime;
        return this;
    }

    public int remainTime() {
        return this.remainTime;
    }

    public ServerboundTimerPacket playTime(final int playTime) {
        this.playTime = playTime;
        return this;
    }

    public int playTime() {
        return this.playTime;
    }

    @Override
    public int packetId() {
        return 65;
    }

    @Override
    public void read(final ByteBuf buffer) {
        this.remainTime = buffer.readIntLE();
        this.playTime = buffer.readIntLE();
    }
}