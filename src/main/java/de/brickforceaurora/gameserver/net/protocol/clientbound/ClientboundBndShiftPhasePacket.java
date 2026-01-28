package de.brickforceaurora.gameserver.net.protocol.clientbound;

import de.brickforceaurora.gameserver.net.protocol.IClientboundPacket;
import io.netty.buffer.ByteBuf;

public final class ClientboundBndShiftPhasePacket implements IClientboundPacket {

    private int repeat;
    private boolean isBuildPhase;

    public ClientboundBndShiftPhasePacket repeat(final int repeat) {
        this.repeat = repeat;
        return this;
    }

    public int repeat() {
        return this.repeat;
    }

    public ClientboundBndShiftPhasePacket isBuildPhase(final boolean isBuildPhase) {
        this.isBuildPhase = isBuildPhase;
        return this;
    }

    public boolean isBuildPhase() {
        return this.isBuildPhase;
    }

    @Override
    public int packetId() {
        return 344;
    }

    @Override
    public void write(final ByteBuf buffer) {
        buffer.writeIntLE(this.repeat);
        buffer.writeBoolean(this.isBuildPhase);
    }
}