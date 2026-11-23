package de.brickforceaurora.server.net.tcp.protocol.clientbound.original;

import de.brickforceaurora.server.net.tcp.protocol.IClientboundPacket;
import de.brickforceaurora.server.net.tcp.protocol.PacketBuf;

public final class ClientboundTimerPacket implements IClientboundPacket {

    private int remainTime;
    private int playTime;

    public ClientboundTimerPacket remainTime(final int remainTime) {
        this.remainTime = remainTime;
        return this;
    }

    public int remainTime() {
        return this.remainTime;
    }

    public ClientboundTimerPacket playTime(final int playTime) {
        this.playTime = playTime;
        return this;
    }

    public int playTime() {
        return this.playTime;
    }

    @Override
    public int packetId() {
        return 66;
    }

    @Override
    public void write(final PacketBuf buf) {
        buf.writeInt(this.remainTime);
        buf.writeInt(this.playTime);
    }
}
