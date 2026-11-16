package de.brickforceaurora.server.net.protocol.serverbound.original;

import de.brickforceaurora.server.net.protocol.IServerboundPacket;
import de.brickforceaurora.server.net.protocol.PacketBuf;

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
    public void read(final PacketBuf buf) {
        this.remainTime = buf.readInt();
        this.playTime = buf.readInt();
    }
}