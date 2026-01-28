package de.brickforceaurora.gameserver.net.protocol.serverbound;

import de.brickforceaurora.gameserver.net.protocol.IServerboundPacket;
import io.netty.buffer.ByteBuf;

public final class ServerboundBndShiftPhasePacket implements IServerboundPacket {

    private int repeat;
    private boolean isBuildPhase;

    public ServerboundBndShiftPhasePacket repeat(final int repeat) {
        this.repeat = repeat;
        return this;
    }

    public int repeat() {
        return this.repeat;
    }

    public ServerboundBndShiftPhasePacket isBuildPhase(final boolean isBuildPhase) {
        this.isBuildPhase = isBuildPhase;
        return this;
    }

    public boolean isBuildPhase() {
        return this.isBuildPhase;
    }

    @Override
    public int packetId() {
        return 349;
    }

    @Override
    public void read(final ByteBuf buffer) {
        this.repeat = buffer.readIntLE();
        this.isBuildPhase = buffer.readBoolean();
    }
}