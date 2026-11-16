package de.brickforceaurora.server.net.protocol.serverbound.original;

import de.brickforceaurora.server.net.protocol.IServerboundPacket;
import de.brickforceaurora.server.net.protocol.PacketBuf;

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
    public void read(final PacketBuf buf) {
        this.repeat = buf.readInt();
        this.isBuildPhase = buf.readBoolean();
    }
}